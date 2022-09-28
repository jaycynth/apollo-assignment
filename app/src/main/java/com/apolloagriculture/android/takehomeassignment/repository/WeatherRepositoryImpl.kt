package com.apolloagriculture.android.takehomeassignment.repository

import com.apolloagriculture.android.takehomeassignment.data.remote.ApiService
import com.apolloagriculture.android.takehomeassignment.model.Weather
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getWeatherData(): Response<Weather> =
        apiService.getWeatherData()


}