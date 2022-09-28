package com.apolloagriculture.android.takehomeassignment.data.remote

import com.apolloagriculture.android.takehomeassignment.model.Weather
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("weather.json")
    suspend fun getWeatherData(): Response<Weather>

}