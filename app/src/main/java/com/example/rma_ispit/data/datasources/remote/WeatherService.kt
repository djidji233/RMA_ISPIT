package com.example.rma_ispit.data.datasources.remote

import com.example.rma_ispit.data.models.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("v1/forecast.json?")
    fun getWeather(@Query("q") city: String, @Query("days") days : Int): Observable<WeatherResponse>

}