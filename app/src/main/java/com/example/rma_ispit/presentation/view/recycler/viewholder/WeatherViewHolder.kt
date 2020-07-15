package com.example.rma_ispit.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_ispit.R
import com.example.rma_ispit.data.models.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.weather_list_item.*

class WeatherViewHolder (
    override val containerView : View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.setOnClickListener{
            onItemClicked(adapterPosition)
        }
    }

    fun bind(weather: Weather){
        Picasso
            .get()
            .load("https:"+weather.icon)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(icon)

        var float = 0.0f
        if(adapterPosition==0){
            float = weather.currenttemp
        } else {
            float = weather.avgtemp
        }

        temperatureTv.text = String.format("%.1f°", float)

        nameTv.text = weather.name
        dateTv.text = weather.date

    }

}