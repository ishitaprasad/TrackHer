from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
import numpy as np
from datetime import datetime, timedelta

from database import init_db, get_connection
from models import cycle_model, feature_scaler, target_scaler, pcos_model


WINDOW_SIZE = 3
FEATURES = 7

app = FastAPI()

init_db()


# ============================
# REQUEST MODELS
# ============================

class FirstCycleInput(BaseModel):

    user_id:int
    last_period_start_date:str
    cycle_length_range:str
    period_duration:str
    flow:str
    regularity:str
    age:float
    bmi:float


class CyclePredictionInput(BaseModel):

    user_id:int
    last_period_start_date:str


class AddCycleInput(BaseModel):

    user_id:int
    cycle_length:int
    menses_length:int
    ovulation_day:int
    luteal_phase:int
    peak:int
    period_start_date:str


class PCOSPredictionInput(BaseModel):

    user_id:int
    age:float
    bmi:float
    weight_gain:int
    hair_growth:int
    skin_darkening:int
    hair_loss:int
    pimples:int
    fast_food:int
    exercise:int


# ============================
# FIRST TIME PREDICTION
# ============================

@app.post("/predict-first-cycle")

def predict_first_cycle(data:FirstCycleInput):

    cycle_map = {

        "21–28 days":26,
        "29–35 days":32,
        "36-40 days":38,
        "I am not sure":30
    }

    menses_map = {

        "Less than 3 days":2,
        "3-5 days":4,
        "6-7 days":6,
        "More than 7 days":8
    }

    predicted_cycle = cycle_map[data.cycle_length_range]
    predicted_menses = menses_map[data.period_duration]

    predicted_ovulation = predicted_cycle - 14
    predicted_luteal = 14

    last_period = datetime.strptime(data.last_period_start_date,"%Y-%m-%d")

    next_period = last_period + timedelta(days=predicted_cycle)
    ovulation_date = last_period + timedelta(days=predicted_ovulation)

    fertile_start = ovulation_date - timedelta(days=5)
    fertile_end = ovulation_date + timedelta(days=1)

    return {

        "predicted_cycle_length":predicted_cycle,
        "next_period_start_date":next_period.strftime("%Y-%m-%d"),
        "predicted_ovulation_date":ovulation_date.strftime("%Y-%m-%d"),
        "fertile_window_start":fertile_start.strftime("%Y-%m-%d"),
        "fertile_window_end":fertile_end.strftime("%Y-%m-%d"),
        "predicted_menses_duration":predicted_menses,
        "predicted_luteal_phase":predicted_luteal
    }


# ============================
# SAVE CYCLE
# ============================

@app.post("/add-cycle")

def add_cycle(data:AddCycleInput):

    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
    INSERT INTO cycles
    (user_id,cycle_length,menses_length,ovulation_day,luteal_phase,peak,period_start_date)
    VALUES(?,?,?,?,?,?,?)
    """,(

        data.user_id,
        data.cycle_length,
        data.menses_length,
        data.ovulation_day,
        data.luteal_phase,
        data.peak,
        data.period_start_date

    ))

    conn.commit()
    conn.close()

    return {"status":"cycle saved"}


# ============================
# LSTM PREDICTION
# ============================

@app.post("/predict-cycle-lstm")

def predict_cycle(data:CyclePredictionInput):

    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""

    SELECT cycle_length,menses_length,ovulation_day,luteal_phase
    FROM cycles
    WHERE user_id=?
    ORDER BY id DESC
    LIMIT 3

    """,(data.user_id,))

    rows = cur.fetchall()

    if len(rows) < WINDOW_SIZE:

        raise HTTPException(
            status_code=400,
            detail="Not enough history for LSTM"
        )

    history = []

    for r in rows:

        cycle,menses,ovulation,luteal = r

        history.append([

            cycle,
            menses,
            ovulation,
            luteal,
            25,   # age placeholder
            24,   # bmi placeholder
            1

        ])

    history = history[::-1]

    user_scaled = feature_scaler.transform(

        np.array(history).reshape(-1,FEATURES)

    ).reshape(1,WINDOW_SIZE,FEATURES)

    pred_scaled = cycle_model.predict(user_scaled)

    pred = target_scaler.inverse_transform(pred_scaled)

    predicted_cycle = round(float(pred[0][0]))
    predicted_ovulation = round(float(pred[0][1]))
    predicted_luteal = round(float(pred[0][2]))
    predicted_menses = round(float(pred[0][3]))

    last_period = datetime.strptime(data.last_period_start_date,"%Y-%m-%d")

    next_period = last_period + timedelta(days=predicted_cycle)

    ovulation_date = last_period + timedelta(days=predicted_ovulation)

    fertile_start = ovulation_date - timedelta(days=5)
    fertile_end = ovulation_date + timedelta(days=1)

    return {

        "predicted_cycle_length":predicted_cycle,
        "next_period_start_date":next_period.strftime("%Y-%m-%d"),
        "predicted_ovulation_date":ovulation_date.strftime("%Y-%m-%d"),
        "fertile_window_start":fertile_start.strftime("%Y-%m-%d"),
        "fertile_window_end":fertile_end.strftime("%Y-%m-%d"),
        "predicted_menses_duration":predicted_menses,
        "predicted_luteal_phase":predicted_luteal
    }


# ============================
# PCOS PREDICTION
# ============================

@app.post("/predict-pcos")

def predict_pcos(data:PCOSPredictionInput):

    features = np.array([[
        data.age,
        data.bmi,
        data.weight_gain,
        data.hair_growth,
        data.skin_darkening,
        data.hair_loss,
        data.pimples,
        data.fast_food,
        data.exercise
    ]])

    prediction = int(pcos_model.predict(features)[0])

    return {

        "pcos_prediction":prediction,
        "risk":"High Risk" if prediction==1 else "Low Risk"

    }
