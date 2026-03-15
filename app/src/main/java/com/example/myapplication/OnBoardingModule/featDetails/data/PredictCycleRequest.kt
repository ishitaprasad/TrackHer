package com.example.myapplication.OnBoardingModule.featDetails.data

import com.google.gson.annotations.SerializedName

data class PredictCycleRequest(
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("last_period_start_date")
    val lastPeriodStartDate: String, // Format: "2026-03-01"

    @SerializedName("cycle_length_range")
    val cycleLengthRange: String, // "21–28 days", "29–35 days", "36-40 days", "I am not sure"

    @SerializedName("period_duration")
    val periodDuration: String, // "Less than 3 days", "3-5 days", "6-7 days", "More than 7 days"

    @SerializedName("flow")
    val flow: String, // "Light", "Moderate", "Heavy"

    @SerializedName("regularity")
    val regularity: String, // "Yes, pretty regular", "No, they vary a lot", "I don't know"

    @SerializedName("age")
    val age: Float,

    @SerializedName("bmi")
    val bmi: Float
)