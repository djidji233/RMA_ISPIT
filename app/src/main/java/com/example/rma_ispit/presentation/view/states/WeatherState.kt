package com.example.rma_ispit.presentation.view.states

import com.example.rma_ispit.data.models.Weather

sealed class WeatherState {
    object Loading: WeatherState()
    object DataFetched: WeatherState()
    data class Success(val weather: List<Weather>): WeatherState()
    data class Error(val message: String): WeatherState()
}