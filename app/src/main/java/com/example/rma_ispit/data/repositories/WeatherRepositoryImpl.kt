package com.example.rma_ispit.data.repositories

import com.example.rma_ispit.data.datasources.local.WeatherDao
import com.example.rma_ispit.data.datasources.remote.WeatherService
import com.example.rma_ispit.data.models.Resource
import com.example.rma_ispit.data.models.Weather
import com.example.rma_ispit.data.models.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber

class WeatherRepositoryImpl (
    private val localDataSource: WeatherDao,
    private val remoteDataSource: WeatherService
) : WeatherRepository {

    override fun fetchAll(city: String, days: Int): Observable<Resource<Unit>> {
        return remoteDataSource
            .getWeather(city, days)
            .map {
                val entities : MutableList<WeatherEntity> = mutableListOf<WeatherEntity>()
                var ind = 1
                for (d in it.forecast.forecastday) {
                    entities.add(WeatherEntity(
                        ind++,
                        it.location.name,
                        it.location.lat,
                        it.location.lon,
                        it.current.condition.icon,
                        d.date,
                        d.day.maxtemp_c,
                        d.day.mintemp_c,
                        d.day.avgtemp_c,
                        d.day.maxwind_mph,
                        d.day.uv
                    ))
                }
                localDataSource.deleteAndInsertAll(entities)
                Resource.Success(Unit)
            }
    }

    override fun insert(entity: WeatherEntity): Completable {
        Timber.e("Insert")
        return localDataSource.insert(entity)
    }

    override fun insertAll(entities: List<WeatherEntity>): Completable {
        Timber.e("Insert All")
        return localDataSource
            .insertAll(entities)
    }

    override fun deleteAll() {
        Timber.e("Delete all")
        localDataSource.deleteAll()
    }

    override fun deleteAndInsertAll(entities: List<WeatherEntity>) {
        Timber.e("Delete and insert all")
        localDataSource.deleteAndInsertAll(entities)
    }

    override fun getAll(): Observable<List<Weather>> {
        Timber.e("Get all")
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Weather(
                        it.id,
                        it.name,
                        it.lat,
                        it.lon,
                        it.icon,
                        it.date,
                        it.maxtemp,
                        it.mintemp,
                        it.avgtemp,
                        it.wind,
                        it.uv
                    )
                }
            }
    }

}