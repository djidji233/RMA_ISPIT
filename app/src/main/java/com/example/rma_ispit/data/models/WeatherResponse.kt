package com.example.rma_ispit.data.models

class WeatherResponse ( // Fetch sa servera
    val location: Location,
    val current: Current,
    val forecast: Forecast
)