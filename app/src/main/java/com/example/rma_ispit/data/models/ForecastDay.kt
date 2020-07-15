package com.example.rma_ispit.data.models

data class ForecastDay(

    val date : String,
    val date_epoch : Long,
    val day : Day,
    val astro : Astro

)