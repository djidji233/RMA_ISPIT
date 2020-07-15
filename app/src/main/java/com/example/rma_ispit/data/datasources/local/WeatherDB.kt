package com.example.rma_ispit.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rma_ispit.data.models.WeatherEntity


@Database(
    entities = [WeatherEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters()
abstract class WeatherDB : RoomDatabase() {
    abstract fun getWeatherDao() : WeatherDao
}