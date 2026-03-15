from fastapi import FastAPI
from pydantic import BaseModel
import numpy as np
import joblib
import tensorflow as tf
from datetime import datetime, timedelta

app = FastAPI()

# Load models
cycle_model = tf.keras.models.load_model("models/cycle_model.h5")
pcos_model = joblib.load("models/pcos_model.pkl")
scalers = joblib.load("models/scalers.pkl")

feature_scaler = scalers["feature"]
target_scaler = scalers["target"]

WINDOW_SIZE = 3

class CycleRequest(BaseModel):
    cycle_history: list
    last_period_start: str
    age: int
    bmi: float
    weight_gain: int
    hair_growth: int
    skin_darkening: int
    hair_loss: int
    pimples: int
    fast_food: int
    exercise: int

@app.post("/predict-cycle")
def predict_cycle(data: CycleRequest):

    history = np.array(data.cycle_history[-WINDOW_SIZE:])

    history_scaled = feature_scaler.transform(
        history.reshape(-1, history.shape[1])
    ).reshape(1, WINDOW_SIZE, history.shape[1])

    pred_scaled = cycle_model.predict(history_scaled)
    pred = target_scaler.inverse_transform(pred_scaled)

    predicted_cycle = round(float(pred[0][0]))

    last_date = datetime.strptime(data.last_period_start, "%Y-%m-%d")
    next_period = last_date + timedelta(days=predicted_cycle)

    return {
        "next_period_date": next_period.strftime("%Y-%m-%d"),
        "predicted_cycle_length": predicted_cycle
    }

@app.post("/analyze-pcos")
def analyze_pcos(data: CycleRequest):

    cycle_std = np.std(data.cycle_history)
    irregular = 1 if cycle_std > 4 else 0

    cycle_variation = abs(np.mean(data.cycle_history) - 28)
    high_bmi = 1 if data.bmi > 25 else 0

    symptom_score = (
        data.weight_gain +
        data.hair_growth +
        data.skin_darkening +
        data.hair_loss +
        data.pimples
    )

    lifestyle_risk = data.fast_food - data.exercise

    user_features = np.array([[
        data.age,
        data.bmi,
        np.mean(data.cycle_history),
        irregular,
        cycle_variation,
        high_bmi,
        symptom_score,
        lifestyle_risk
    ]])

    probability = pcos_model.predict_proba(user_features)[0][1]

    return {
        "pcos_probability": float(probability),
        "risk_level": (
            "Low" if probability < 0.4 else
            "Medium" if probability < 0.7 else
            "High"
        )
    }
    