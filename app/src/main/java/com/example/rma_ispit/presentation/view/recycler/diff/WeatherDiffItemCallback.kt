package com.example.rma_ispit.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma_ispit.data.models.Weather

class WeatherDiffItemCallback : DiffUtil.ItemCallback<Weather>() {

    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.lat == newItem.lat &&
                oldItem.lon == newItem.lon &&
                oldItem.icon == newItem.icon &&
                oldItem.date == newItem.date &&
                oldItem.maxtemp == newItem.maxtemp &&
                oldItem.mintemp == newItem.mintemp &&
                oldItem.avgtemp == newItem.avgtemp &&
                oldItem.wind == newItem.wind &&
                oldItem.uv == newItem.uv

    }

}