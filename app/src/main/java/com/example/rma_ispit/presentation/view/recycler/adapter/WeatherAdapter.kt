package com.example.rma_ispit.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rma_ispit.R
import com.example.rma_ispit.data.models.Weather
import com.example.rma_ispit.presentation.view.recycler.diff.WeatherDiffItemCallback
import com.example.rma_ispit.presentation.view.recycler.viewholder.WeatherViewHolder

class WeatherAdapter (
    weatherDiffItemCallback: WeatherDiffItemCallback,
    private val onWeatherClicked: (Weather) -> Unit
) : ListAdapter<Weather, WeatherViewHolder>(weatherDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.weather_list_item, parent, false)
        return WeatherViewHolder(
            containerView,
            {
                val weather = getItem(it)
                onWeatherClicked(weather)
            }
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }

}