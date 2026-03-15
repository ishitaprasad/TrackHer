# TrackHer Backend (FastAPI + Machine Learning)

This branch contains the **backend API for the TrackHer Android application**.
It provides endpoints for:

• First-time menstrual cycle prediction (no history)
• LSTM-based cycle prediction using historical data
• PCOS risk prediction
• Cycle data storage for future predictions

The backend is built using **FastAPI**, **TensorFlow**, and **SQLite**.

---

# Architecture Overview

Android App → FastAPI Backend → SQLite Database → ML Models

The backend performs three main tasks:

1. Accept user data from the Android app
2. Store cycle history in the database
3. Run ML models to generate predictions

---

# Backend Folder Structure

```
backend/

main.py              # FastAPI server and endpoints
database.py          # SQLite database setup
models.py            # Loads trained ML models
requirements.txt     # Python dependencies

cycle_model.h5       # LSTM cycle prediction model
feature_scaler.pkl   # Feature scaler used during training
target_scaler.pkl    # Target scaler used during training
pcos_model.pkl       # PCOS prediction model
```

The SQLite database file **trackher.db** will be created automatically when the backend starts.

---

# Requirements

Python version recommended: **Python 3.10**

Install dependencies:

```
pip install -r requirements.txt
```

If multiple Python versions exist:

```
python -m pip install -r requirements.txt
```

---

# Running the Backend

Navigate to the backend folder:

```
cd TrackHer/backend
```

Start the FastAPI server:

```
uvicorn main:app --reload
```

The backend will run at:

```
http://127.0.0.1:8000
```

---

# API Documentation

FastAPI automatically generates API documentation.

Open in browser:

```
http://127.0.0.1:8000/docs
```

This page allows testing all endpoints directly.

---

# Connecting Android App to Backend

When running locally, the Android app **must use the computer's local IP address** instead of localhost.

Example:

```
http://192.168.1.15:8000
```

To find your IP:

Windows:

```
ipconfig
```

Look for:

```
IPv4 Address
```

Use this IP in the Android Retrofit base URL.

Example Retrofit base URL:

```
http://192.168.1.15:8000/
```

---

# Backend Prediction Flow

### First Time User

The user installs the app and answers onboarding questions.

Android sends:

```
POST /predict-first-cycle
```

Backend performs rule-based prediction and returns:

• predicted cycle length
• next period date
• ovulation date
• fertility window
• menses duration
• luteal phase

No history is required.

---

### Saving Cycle Data

Whenever a user logs a cycle, Android sends:

```
POST /add-cycle
```

The backend stores cycle data in SQLite.

This data is used to build the LSTM history window.

---

### LSTM Prediction

Once the user has **at least 3 cycles recorded**, the Android app can request:

```
POST /predict-cycle-lstm
```

Backend retrieves the last 3 cycles and feeds them to the LSTM model.

The model predicts:

• cycle length
• ovulation day
• luteal phase
• menses duration

Dates are calculated and returned to the app.
