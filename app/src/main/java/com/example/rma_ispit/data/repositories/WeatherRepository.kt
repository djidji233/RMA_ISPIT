package com.example.rma_ispit.data.repositories

import com.example.rma_ispit.data.models.Resource
import com.example.rma_ispit.data.models.Weather
import com.example.rma_ispit.data.models.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface WeatherRepository {
    fun fetchAll(city: String, days: Int): Observable<Resource<Unit>>
    fun insert(entity: WeatherEntity) : Completable
    fun insertAll(entities: List<WeatherEntity>): Completable
    fun deleteAll()
    fun deleteAndInsertAll(entities: List<WeatherEntity>)
    fun getAll() : Observable<List<Weather>>
}