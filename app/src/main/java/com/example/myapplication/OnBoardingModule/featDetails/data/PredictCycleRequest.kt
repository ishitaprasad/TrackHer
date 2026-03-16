package com.example.myapplication.OnBoardingModule.featDetails.data

import com.google.gson.annotations.SerializedName

data class PredictCycleRequest(

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("last_period_start_date")
    val lastPeriodStartDate: String,

    @SerializedName("cycle_length_range")
    val cycleLengthRange: String,   // "21–28 days"

    @SerializedName("period_duration")
    val periodDuration: String      // "3-5 days"
)

data class PredictCycleResponse(
    val prediction_type: String,

    val predicted_cycle_length: Int,
    val next_period_start_date: String,

    val predicted_ovulation_date: String,

    val fertile_window_start: String,
    val fertile_window_end: String,

    val predicted_menses_duration: Int,
    val predicted_luteal_phase: Int
)