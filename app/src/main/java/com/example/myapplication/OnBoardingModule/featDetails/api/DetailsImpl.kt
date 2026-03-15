package com.example.myapplication.OnBoardingModule.featDetails.api

import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import com.example.myapplication.api.RetrofitClient
import retrofit2.Response

class DetailsImpl : DetailsApi {

    override suspend fun predictCycle(request: PredictCycleRequest): Response<Any> {
        return RetrofitClient.apiService.predictCycle(request)
    }

    override suspend fun predictPcos(request: PredictPcosRequest): Response<Any> {
        return RetrofitClient.apiService.predictPcos(request)
    }
}
