package com.example.myapplication.OnBoardingModule.featDetails.api

import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleResponse
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosResponse
import retrofit2.Response
interface DetailsApi {

    suspend fun predictCycle(
        request: PredictCycleRequest
    ): Response<PredictCycleResponse>

    suspend fun predictPcos(
        request: PredictPcosRequest
    ): Response<PredictPcosResponse>
}