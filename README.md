# TrackHer Backend – Developer README

This branch contains the **backend API for the TrackHer Android application**.
The backend handles:

* Cycle prediction (first-time users)
* LSTM-based cycle prediction using historical cycles
* PCOS risk prediction
* Storage of cycle history for model input

The backend is implemented using **FastAPI**, **TensorFlow**, and **SQLite**.

---

# Architecture Overview

Android App → FastAPI Backend → SQLite Database → ML Models

The backend performs three main responsibilities:

1. Receive user data from the Android app
2. Store cycle history in the database
3. Run machine learning models to generate predictions

---

# Backend Folder Structure

```text id="struct01"
backend/

main.py              # FastAPI server and API endpoints
database.py          # SQLite database initialization
models.py            # Loads trained ML models
schemas.py           # API request schemas
requirements.txt     # Python dependencies

cycle_model.h5       # LSTM cycle prediction model
feature_scaler.pkl   # Input scaler used during training
target_scaler.pkl    # Output scaler used during training
pcos_model.pkl       # PCOS prediction model
```

The SQLite database file **trackher.db** is automatically created when the backend starts.

---

# Requirements

Python version recommended: **Python 3.10**

Install dependencies:

```bash id="install01"
pip install -r requirements.txt
```

If multiple Python versions exist on your system:

```bash id="install02"
python -m pip install -r requirements.txt
```

---

# Running the Backend

Navigate to the backend folder:

```bash id="run01"
cd TrackHer/backend
```

Start the FastAPI server:

```bash id="run02"
uvicorn main:app --reload
```

The backend will run at:

```text id="run03"
http://127.0.0.1:8000
```

---

# API Documentation

FastAPI automatically generates interactive API documentation.

Open in browser:

```text id="docs01"
http://127.0.0.1:8000/docs
```

This page allows testing all endpoints.

---

# Connecting Android App to Backend

When running locally, Android must call the backend using the **computer's local IP address**, not localhost.

Example:

```text id="ip01"
http://192.168.1.15:8000
```

To find your IP address:

Windows:

```bash id="ip02"
ipconfig
```

Look for:

```text id="ip03"
IPv4 Address
```

Use this IP in the Android Retrofit base URL.

Example Retrofit base URL:

```text id="ip04"
http://192.168.1.15:8000/
```

---

# Backend Endpoints

The frontend only needs to interact with **three endpoints**.

---

# 1. Predict Cycle

Endpoint:

```text id="endpoint01"
POST /predict-cycle
```

This endpoint is used for **both first-time users and returning users**.

The backend automatically decides whether to use:

* rule-based prediction (first-time user)
* LSTM prediction (if ≥ 3 cycles exist in database)

Frontend request fields:

user_id : int
last_period_start_date : string (YYYY-MM-DD)

cycle_length_range : string
period_duration : string
flow : string
regularity : string

age : float
bmi : float

Example request:

```json id="req01"
{
"user_id":1,
"last_period_start_date":"2026-03-01",
"cycle_length_range":"29–35 days",
"period_duration":"3-5 days",
"flow":"Moderate",
"regularity":"Yes, pretty regular",
"age":24,
"bmi":26.3
}
```

Response includes:

* predicted cycle length
* next period date
* ovulation date
* fertility window
* menses duration
* luteal phase

---

# 2. Add Cycle History

Endpoint:

```text id="endpoint02"
POST /add-cycle
```

This endpoint stores cycle history for future LSTM predictions.

Frontend request fields:

user_id : int
cycle_length : int
menses_length : int
period_start_date : string (YYYY-MM-DD)

Example request:

```json id="req02"
{
"user_id":1,
"cycle_length":29,
"menses_length":5,
"period_start_date":"2026-03-01"
}
```

Once **3 cycles exist**, the backend automatically starts using the LSTM model.

---

# 3. PCOS Prediction

Endpoint:

```text id="endpoint03"
POST /predict-pcos
```

Frontend request fields:

user_id : int
age : float
bmi : float

weight_gain : int (0 or 1)
hair_growth : int (0 or 1)
skin_darkening : int (0 or 1)
hair_loss : int (0 or 1)
pimples : int (0 or 1)
fast_food : int (0 or 1)
exercise : int (0 or 1)

Example request:

```json id="req03"
{
"user_id":1,
"age":24,
"bmi":26.1,
"weight_gain":1,
"hair_growth":0,
"skin_darkening":0,
"hair_loss":1,
"pimples":1,
"fast_food":1,
"exercise":0
}
```

Response returns:

* PCOS prediction (0 or 1)
* risk classification

---

# LSTM Model Features

The cycle prediction model was trained using the following features:

```text id="model01"
LengthofCycle
LengthofMenses
EstimatedDayofOvulation
LengthofLutealPhase
Age
BMI
CycleWithPeakorNot
```

Frontend **does not send these derived features**.

The backend calculates them automatically:

```text id="model02"
ovulation_day = cycle_length − 14
luteal_phase = 14
peak = 1
```

The final input sequence used by the LSTM model:

```text id="model03"
[
cycle_length,
menses_length,
ovulation_day,
luteal_phase,
age,
bmi,
peak
]
```

Sequence window size:

```text id="model04"
WINDOW_SIZE = 3 cycles
```

---

# Frontend Prediction Flow

App install
→ User answers onboarding questions
→ POST /predict-cycle

User logs cycles
→ POST /add-cycle

Once ≥ 3 cycles exist
→ POST /predict-cycle

Backend automatically switches to the LSTM model.

---

# Notes for Frontend Developers

* Always store cycles using `/add-cycle`
* Minimum 3 cycles are required for LSTM predictions
* Dates must use format **YYYY-MM-DD**
* Use the machine's local IP address when connecting from Android emulator

---

# Future Improvements

Planned improvements include:

* user authentication
* cloud database deployment
* ovulation tracking
* push notifications
* model retraining pipeline

---

TrackHer Backend
Machine Learning + API layer powering the Android application.
