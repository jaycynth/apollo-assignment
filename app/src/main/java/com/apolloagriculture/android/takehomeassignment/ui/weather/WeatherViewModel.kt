package com.apolloagriculture.android.takehomeassignment.ui.weather

import androidx.lifecycle.ViewModel
import com.apolloagriculture.android.takehomeassignment.repository.WeatherRepository
import com.apolloagriculture.android.takehomeassignment.util.dataAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    suspend fun getWeatherData() = dataAccess {
        repository.getWeatherData()
    }


}