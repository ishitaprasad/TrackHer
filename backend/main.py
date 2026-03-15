from fastapi import FastAPI, HTTPException
import numpy as np
from datetime import datetime, timedelta
import tensorflow as tf
from database import init_db, get_connection
from models import cycle_model, feature_scaler, target_scaler, pcos_model
from schemas import PredictCycleInput, AddCycleInput, PCOSPredictionInput


WINDOW_SIZE = 3
FEATURES = 7

app = FastAPI()

init_db()


# ==========================
# FIRST PREDICTION
# ==========================

def first_cycle_prediction(data):

    cycle_map = {
        "21–28 days": 26,
        "29–35 days": 32,
        "36-40 days": 38,
        "I am not sure": 30
    }

    menses_map = {
        "Less than 3 days": 2,
        "3-5 days": 4,
        "6-7 days": 6,
        "More than 7 days": 8
    }

    predicted_cycle = cycle_map[data.cycle_length_range]
    predicted_menses = menses_map[data.period_duration]

    predicted_ovulation = predicted_cycle - 14
    predicted_luteal = 14

    last_period = datetime.strptime(data.last_period_start_date, "%Y-%m-%d")

    next_period = last_period + timedelta(days=predicted_cycle)
    ovulation_date = last_period + timedelta(days=predicted_ovulation)

    fertile_start = ovulation_date - timedelta(days=5)
    fertile_end = ovulation_date + timedelta(days=1)

    return {

        "prediction_type": "first_time",

        "predicted_cycle_length": predicted_cycle,
        "next_period_start_date": next_period.strftime("%Y-%m-%d"),

        "predicted_ovulation_date": ovulation_date.strftime("%Y-%m-%d"),

        "fertile_window_start": fertile_start.strftime("%Y-%m-%d"),
        "fertile_window_end": fertile_end.strftime("%Y-%m-%d"),

        "predicted_menses_duration": predicted_menses,
        "predicted_luteal_phase": predicted_luteal
    }


# ==========================
# LSTM PREDICTION
# ==========================

def lstm_prediction(user_id, last_period_date):

    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""

    SELECT cycle_length,menses_length,ovulation_day,luteal_phase
    FROM cycles
    WHERE user_id=?
    ORDER BY id DESC
    LIMIT 3

    """, (user_id,))

    rows = cur.fetchall()

    if len(rows) < WINDOW_SIZE:
        raise HTTPException(status_code=400, detail="Not enough history")

    history = []

    for r in rows:

        cycle, menses, ovulation, luteal = r

        history.append([

            cycle,
            menses,
            ovulation,
            luteal,
            25,
            24,
            1

        ])

    history = history[::-1]

    scaled = feature_scaler.transform(
        np.array(history).reshape(-1, FEATURES)
    ).reshape(1, WINDOW_SIZE, FEATURES)

    pred_scaled = cycle_model.signatures["serving_default"](
        tf.constant(scaled)
    )

    pred_scaled = list(pred_scaled.values())[0].numpy()

    pred = target_scaler.inverse_transform(pred_scaled)

    predicted_cycle = round(float(pred[0][0]))
    predicted_ovulation = round(float(pred[0][1]))
    predicted_luteal = round(float(pred[0][2]))
    predicted_menses = round(float(pred[0][3]))

    last_period = datetime.strptime(last_period_date, "%Y-%m-%d")

    next_period = last_period + timedelta(days=predicted_cycle)

    ovulation_date = last_period + timedelta(days=predicted_ovulation)

    fertile_start = ovulation_date - timedelta(days=5)
    fertile_end = ovulation_date + timedelta(days=1)

    return {

        "prediction_type": "lstm",

        "predicted_cycle_length": predicted_cycle,
        "next_period_start_date": next_period.strftime("%Y-%m-%d"),

        "predicted_ovulation_date": ovulation_date.strftime("%Y-%m-%d"),

        "fertile_window_start": fertile_start.strftime("%Y-%m-%d"),
        "fertile_window_end": fertile_end.strftime("%Y-%m-%d"),

        "predicted_menses_duration": predicted_menses,
        "predicted_luteal_phase": predicted_luteal
    }


# ==========================
# PREDICT CYCLE
# ==========================

@app.post("/predict-cycle")

def predict_cycle(data: PredictCycleInput):

    conn = get_connection()
    cur = conn.cursor()

    cur.execute(

        "SELECT COUNT(*) FROM cycles WHERE user_id=?",
        (data.user_id,)

    )

    cycle_count = cur.fetchone()[0]

    if cycle_count < WINDOW_SIZE:

        return first_cycle_prediction(data)

    else:

        return lstm_prediction(data.user_id, data.last_period_start_date)


# ==========================
# ADD CYCLE
# ==========================

@app.post("/add-cycle")

def add_cycle(data: AddCycleInput):

    ovulation_day = data.cycle_length - 14
    luteal_phase = 14
    peak = 1

    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""

    INSERT INTO cycles
    (user_id,cycle_length,menses_length,ovulation_day,luteal_phase,peak,period_start_date)

    VALUES(?,?,?,?,?,?,?)

    """, (

        data.user_id,
        data.cycle_length,
        data.menses_length,
        ovulation_day,
        luteal_phase,
        peak,
        data.period_start_date

    ))

    conn.commit()
    conn.close()

    return {"status": "cycle stored"}


# ==========================
# PCOS PREDICTION
# ==========================

@app.post("/predict-pcos")

def predict_pcos(data: PCOSPredictionInput):

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

        "pcos_prediction": prediction,
        "risk": "High Risk" if prediction == 1 else "Low Risk"

    }
