package com.example.rma_ispit.modules


import com.example.rma_ispit.data.datasources.local.WeatherDB
import com.example.rma_ispit.data.datasources.remote.WeatherService
import com.example.rma_ispit.data.repositories.WeatherRepository
import com.example.rma_ispit.data.repositories.WeatherRepositoryImpl
import com.example.rma_ispit.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    viewModel { MainViewModel(weatherRepository = get()) }

    single<WeatherRepository> { WeatherRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<WeatherDB>().getWeatherDao() }

    single<WeatherService> { create(retrofit = get()) }

}

