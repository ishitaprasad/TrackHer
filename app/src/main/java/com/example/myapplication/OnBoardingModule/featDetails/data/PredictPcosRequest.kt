package com.example.myapplication.OnBoardingModule.featDetails.data

import com.google.gson.annotations.SerializedName

data class PredictPcosRequest(
    @SerializedName("age")
    val age: Int,

    @SerializedName("bmi")
    val bmi: Float,

    @SerializedName("weight_gain")
    val weightGain: Int, // 1 for Yes, 0 for No

    @SerializedName("hair_growth")
    val hairGrowth: Int, // 1 for Yes, 0 for No

    @SerializedName("skin_darkening")
    val skinDarkening: Int, // 1 for Yes, 0 for No

    @SerializedName("hair_loss")
    val hairLoss: Int, // 1 for Yes, 0 for No

    @SerializedName("pimples")
    val pimples: Int, // 1 for Yes, 0 for No

    @SerializedName("fast_food")
    val fastFood: Int, // 1 for Yes, 0 for No

    @SerializedName("exercise")
    val exercise: Int // 1 for Yes, 0 for No
)

data class PredictPcosResponse(

    @SerializedName("pcos_prediction")
    val pcosPrediction: Int,

    @SerializedName("risk")
    val risk: String
)
