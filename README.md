# TrackHer
# TrackHer Backend (FastAPI + Machine Learning)

This backend powers the **TrackHer Android application**, providing APIs for:

* Menstrual cycle prediction
* PCOS risk prediction

The backend is built using **FastAPI** and serves trained machine learning models.

---

# Project Structure

```
backend/
│
├── main.py                # FastAPI backend code
├── cycle_model.h5         # TensorFlow cycle prediction model
├── pcos_model.pkl         # PCOS prediction model
├── feature_scaler.pkl     # Feature scaler for cycle model
├── target_scaler.pkl      # Target scaler for cycle model
├── requirements.txt       # Python dependencies
└── README.md
```

---

# Requirements

* Python **3.10**
* pip package manager

---

# Step 1 — Navigate to Backend Folder

Open terminal and go to the backend directory.

```
cd TrackHer/backend
```

---

# Step 2 — Install Dependencies

Install all required libraries using:

```
pip install -r requirements.txt

```
---

# Step 3 — Start the Backend Server

Run the FastAPI server using **uvicorn**:

```
uvicorn main:app --reload
```

If uvicorn is not recognized:

```
python -m uvicorn main:app --reload
```

Server will start at:

```
http://127.0.0.1:8000
```

---

# Step 4 — Test API

Open the API documentation in your browser:

```
http://127.0.0.1:8000/docs
```

This provides an interactive interface to test all endpoints.

---

# Available APIs

## 1. Cycle Prediction

Endpoint:

```
POST /predict-cycle
```

Example request:

```
{
  "cycle_history": [28, 29, 30, 27, 28]
}
```

Example response:

```
{
  "predicted_cycle_length": 29.4
}
```

---

## 2. PCOS Prediction

Endpoint:

```
POST /predict-pcos
```

Example request:

```
{
  "predicted_cycle_length": 29,
  "age": 24,
  "bmi": 26,
  "weight_gain": 1,
  "hair_growth": 0,
  "skin_darkening": 0,
  "hair_loss": 1,
  "pimples": 1,
  "fast_food": 1,
  "exercise": 0
}
```

Example response:

```
{
  "prediction": 1,
  "result": "PCOS Risk"
}
```

---

# Connecting to Android App

When running the backend locally, Android Studio should use your **local machine IP** instead of localhost.

Example:

```
http://192.168.1.10:8000
```

This allows the Android emulator or device to communicate with the backend.

---

# Notes

* Ensure all model files (`.h5`, `.pkl`) are present in the backend directory.
* Use Python **3.10** for compatibility with TensorFlow.
* Restart the server after modifying backend code.
