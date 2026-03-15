package com.example.myapplication.api

import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict-cycle")
    suspend fun predictCycle(@Body request: PredictCycleRequest): Response<Any>

    @POST("predict-pcos")
    suspend fun predictPcos(@Body request: PredictPcosRequest): Response<Any>
}
