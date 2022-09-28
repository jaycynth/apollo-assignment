package com.apolloagriculture.android.takehomeassignment.repository

import com.apolloagriculture.android.takehomeassignment.model.Weather
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherData(): Response<Weather>
}