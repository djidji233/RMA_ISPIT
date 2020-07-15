package com.example.rma_ispit.data.models

data class Current(
    val last_updated_epoch : Long,
    val last_updated : String,
    val temp_c : Float,
    val temp_f : Float,
    val is_day : Int,
    val condition: Condition,
    val wind_mph : Float,
    val wind_kph : Float,
    val wind_degree : Int,
    val wind_dir : String,
    val pressure_mb : Float,
    val pressure_in : Float,
    val precip_mm : Float,
    val precip_in : Float,
    val humidity : Int,
    val cloud : Int,
    val feelslike_c : Float,
    val feelslike_f : Float,
    val vis_km : Float,
    val vis_miles : Float,
    val uv : Float,
    val gust_mph : Float,
    val gust_kph : Float
)