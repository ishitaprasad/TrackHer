from tensorflow.keras.models import load_model
import joblib
import tensorflow as tf

cycle_model = tf.saved_model.load("cycle_model")


feature_scaler = joblib.load("feature_scaler.pkl")
target_scaler = joblib.load("target_scaler.pkl")

pcos_model = joblib.load("pcos_model.pkl")
