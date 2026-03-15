from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import numpy as np
import joblib
import tensorflow as tf
from datetime import datetime, timedelta
from typing import List

# ==========================================================
# CONFIG
# ==========================================================

WINDOW_SIZE = 10   # must match training notebook
FEATURES = 7       # number of features per cycle

# ==========================================================
# LOAD MODELS
# ==========================================================

cycle_model = tf.keras.models.load_model("cycle_model.h5")

feature_scaler = joblib.load("feature_scaler.pkl")
target_scaler = joblib.load("target_scaler.pkl")

pcos_model = joblib.load("pcos_model.pkl")

# ==========================================================
# FASTAPI APP
# ==========================================================

app = FastAPI(
    title="TrackHer ML Backend",
    description="Backend API for cycle prediction and PCOS risk prediction",
    version="1.0"
)

# ==========================================================
# REQUEST SCHEMAS
# ==========================================================

class CyclePredictionInput(BaseModel):

    last_period_start_date: str

    user_history: List[List[float]]
    # shape = WINDOW_SIZE x FEATURES


class PCOSPredictionInput(BaseModel):

    age: float
    bmi: float
    weight_gain: int
    hair_growth: int
    skin_darkening: int
    hair_loss: int
    pimples: int
    fast_food: int
    exercise: int


# ==========================================================
# HEALTH CHECK
# ==========================================================

@app.get("/")
def home():
    return {"message": "TrackHer Backend Running"}

# ==========================================================
# CYCLE PREDICTION FUNCTION
# ==========================================================

def predict_user_next_cycle_with_dates(user_history, last_period_start_date):

    user_history = np.array(user_history)

    if user_history.shape != (WINDOW_SIZE, FEATURES):
        raise ValueError(
            f"Input must be shape ({WINDOW_SIZE}, {FEATURES})"
        )

    # Scale input
    user_scaled = feature_scaler.transform(
        user_history.reshape(-1, FEATURES)
    ).reshape(1, WINDOW_SIZE, FEATURES)

    # Predict
    pred_scaled = cycle_model.predict(user_scaled)

    pred = target_scaler.inverse_transform(pred_scaled)

    predicted_cycle = round(float(pred[0][0]))
    predicted_ovulation_day = round(float(pred[0][1]))
    predicted_luteal = round(float(pred[0][2]))
    predicted_menses = round(float(pred[0][3]))

    # Convert last period date
    last_period_date = datetime.strptime(
        last_period_start_date, "%Y-%m-%d"
    )

    # Next period
    next_period_date = last_period_date + timedelta(days=predicted_cycle)

    # Ovulation date
    ovulation_date = last_period_date + timedelta(days=predicted_ovulation_day)

    # Fertile window
    fertile_start = ovulation_date - timedelta(days=5)
    fertile_end = ovulation_date + timedelta(days=1)

    return {

        "predicted_cycle_length": predicted_cycle,

        "next_period_start_date":
            next_period_date.strftime("%Y-%m-%d"),

        "predicted_ovulation_date":
            ovulation_date.strftime("%Y-%m-%d"),

        "fertile_window_start":
            fertile_start.strftime("%Y-%m-%d"),

        "fertile_window_end":
            fertile_end.strftime("%Y-%m-%d"),

        "predicted_menses_duration":
            predicted_menses,

        "predicted_luteal_phase":
            predicted_luteal
    }

# ==========================================================
# API ENDPOINT: CYCLE PREDICTION
# ==========================================================

@app.post("/predict-cycle")
def predict_cycle(data: CyclePredictionInput):

    try:

        result = predict_user_next_cycle_with_dates(
            data.user_history,
            data.last_period_start_date
        )

        return result

    except Exception as e:

        raise HTTPException(
            status_code=400,
            detail=str(e)
        )

# ==========================================================
# API ENDPOINT: PCOS PREDICTION
# ==========================================================

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

    if prediction == 1:
        risk = "High Risk of PCOS"
    else:
        risk = "Low Risk of PCOS"

    return {
        "pcos_prediction": prediction,
        "risk_level": risk
    }
