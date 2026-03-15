package com.example.myapplication.OnBoardingModule.featDetails.api

import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import retrofit2.Response

interface DetailsApi {
    suspend fun predictCycle(request: PredictCycleRequest): Response<Any>
    suspend fun predictPcos(request: PredictPcosRequest): Response<Any>
}