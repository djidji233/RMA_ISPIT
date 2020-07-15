package com.example.rma_ispit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity ( // DB
    @PrimaryKey(autoGenerate = true) val id: Int,

    // location -> name, lat, lon
    val name : String,
    val lat : Float,
    val lon : Float,

    // current -> condition -> icon
    val icon : String,

    // forecast -> forecastday -> date
    val date : String,
    // forecast -> forecastday -> day -> maxtemp_c, mintemp_c, avgtemp_c, maxwind_mph, uv
    val maxtemp : Float,
    val mintemp : Float,
    val avgtemp : Float,
    val wind : Float,
    val uv : Float,

    val currenttemp : Float
)