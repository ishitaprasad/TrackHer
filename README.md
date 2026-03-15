---

# 🚀 Running the TrackHer Backend

Follow these steps to run the backend locally and connect the frontend.

---

# 1️⃣ Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/ishitaprasad/TrackHer.git
cd TrackHer
```

---

# 2️⃣ Install Python

Install:

```
Python 3.10.11
```

Download from:

```
https://www.python.org/downloads/release/python-31011/
```

⚠️ Make sure to check **"Add Python to PATH"** during installation.

---

# 3️⃣ Create a Virtual Environment

From the project root folder:

```bash
python -m venv venv
```

Activate it.

### Windows

```bash
venv\Scripts\activate
```

After activation you should see:

```
(venv)
```

in your terminal.

---

# 4️⃣ Navigate to Backend Folder

```bash
cd backend
```

---

# 5️⃣ Install Dependencies

Install all required packages:

```bash
pip install -r requirements.txt
```

This installs the backend dependencies including **TensorFlow**, **scikit-learn**, and **FastAPI**.

---

# 6️⃣ Start the Backend Server

Run the following command:

```bash
python -m uvicorn main:app --reload
```

If successful, you should see:

```
Uvicorn running on http://127.0.0.1:8000
Application startup complete
```

---

# 7️⃣ Open API Documentation

Open your browser and go to:

```
http://127.0.0.1:8000/docs
```

This page shows all backend API endpoints and allows testing requests.

---

# 🔗 Connecting the Frontend

The frontend should use the following base API URL:

```
http://127.0.0.1:8000
```

Example API endpoint:

```
POST /add-cycle
```

Full request URL:

```
http://127.0.0.1:8000/add-cycle
```

---

# 📦 Example Request (Cycle Data)

Example JSON request body:

```json
{
  "user_id": 1,
  "cycle_length": 28,
  "menses_length": 5,
  "luteal_phase": 14,
  "peak_day": 13
}
```

---

# 📦 Example Request (PCOS Prediction)

```json
{
  "age": 25,
  "weight": 60,
  "cycle_length": 35,
  "irregular_cycles": 1,
  "acne": 0,
  "hair_growth": 1
}
```

---

# 🛑 Stopping the Backend

Press:

```
CTRL + C
```

in the terminal where the server is running.

---

# ⚠️ Important Notes

* The backend must be running **before starting the frontend**.
* All API responses are returned in **JSON format**.
* If any dependency errors occur, reinstall packages using:

```bash
pip install -r requirements.txt
```

---

✅ After completing these steps, the backend will be running locally and the frontend can send requests to the API.

---
