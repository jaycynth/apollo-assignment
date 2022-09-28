package com.apolloagriculture.android.takehomeassignment.model

data class Weather(
    val dayAfterTomorrow: DayWeather,
    val today: DayWeather,
    val tomorrow: DayWeather
)