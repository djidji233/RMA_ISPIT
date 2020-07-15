package com.example.rma_ispit.data.models

data class Weather ( // UI
    val id: Int,
    val name : String,
    val lat : Float,
    val lon : Float,

    val icon : String,

    val date : String,

    val maxtemp : Float,
    val mintemp : Float,
    val avgtemp : Float,
    val wind : Float,
    val uv : Float
)