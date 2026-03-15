from fastapi import FastAPI
from pydantic import BaseModel
import numpy as np
import joblib
import tensorflow as tf

# ---------------------------------------------------
# Load Models
# ---------------------------------------------------

cycle_model = tf.keras.models.load_model("cycle_model.h5")
feature_scaler = joblib.load("feature_scaler.pkl")
target_scaler = joblib.load("target_scaler.pkl")

pcos_model = joblib.load("pcos_model.pkl")

# ---------------------------------------------------
# Create App
# ---------------------------------------------------

app = FastAPI()

# ---------------------------------------------------
# Request Schemas
# ---------------------------------------------------

class CycleInput(BaseModel):
    cycle_history: list   # example [28,29,30,27,28]

class PCOSInput(BaseModel):

    predicted_cycle_length: float
    age: int
    bmi: float

    weight_gain: int
    hair_growth: int
    skin_darkening: int
    hair_loss: int
    pimples: int

    fast_food: int
    exercise: int


# ---------------------------------------------------
# Cycle Prediction Endpoint
# ---------------------------------------------------

@app.post("/predict-cycle")
def predict_cycle(data: CycleInput):

    history = np.array(data.cycle_history).reshape(1, -1)

    scaled = feature_scaler.transform(history)

    scaled = scaled.reshape((1, scaled.shape[1], 1))

    pred = cycle_model.predict(scaled)

    cycle_length = target_scaler.inverse_transform(pred)[0][0]

    return {
        "predicted_cycle_length": float(round(cycle_length,2))
    }


# ---------------------------------------------------
# PCOS Prediction Endpoint
# ---------------------------------------------------

@app.post("/predict-pcos")
def predict_pcos(data: PCOSInput):

    features = np.array([[
        data.predicted_cycle_length,
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

    prediction = pcos_model.predict(features)[0]

    result = "PCOS Risk" if prediction == 1 else "Low Risk"

    return {
        "prediction": int(prediction),
        "result": result
    }