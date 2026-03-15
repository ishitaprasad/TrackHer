import sqlite3

DB_NAME = "trackher.db"


def get_connection():
    return sqlite3.connect(DB_NAME)


def init_db():

    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
    CREATE TABLE IF NOT EXISTS users(
        user_id INTEGER PRIMARY KEY AUTOINCREMENT,
        age REAL,
        bmi REAL
    )
    """)

    cur.execute("""
    CREATE TABLE IF NOT EXISTS cycles(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER,
        cycle_length INTEGER,
        menses_length INTEGER,
        ovulation_day INTEGER,
        luteal_phase INTEGER,
        peak INTEGER,
        period_start_date TEXT
    )
    """)

    cur.execute("""
    CREATE TABLE IF NOT EXISTS pcos_records(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER,
        weight_gain INTEGER,
        hair_growth INTEGER,
        skin_darkening INTEGER,
        hair_loss INTEGER,
        pimples INTEGER,
        fast_food INTEGER,
        exercise INTEGER,
        prediction INTEGER
    )
    """)

    conn.commit()
    conn.close()
