package com.example.rma_ispit.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rma_ispit.presentation.view.states.WeatherState

interface MainContract {

    interface ViewModel {

        val weatherState: LiveData<WeatherState>

        fun fetchAll(city : String, days : Int)
        fun getAll()

    }

}