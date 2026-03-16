package com.example.myapplication.api

import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleResponse
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("predict-cycle")
    suspend fun predictCycle(
        @Body request: PredictCycleRequest
    ): Response<PredictCycleResponse>

    @POST("predict-pcos")
    suspend fun predictPcos(
        @Body request: PredictPcosRequest
    ): Response<PredictPcosResponse>
}
