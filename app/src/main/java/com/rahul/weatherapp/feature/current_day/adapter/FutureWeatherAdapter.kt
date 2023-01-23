package com.rahul.weatherapp.feature.current_day.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.weatherapp.R
import com.rahul.weatherapp.Utils.Util

class FutureWeatherAdapter(private val weatherList: ArrayList<com.rahul.weatherapp.model.List>) :
    RecyclerView.Adapter<FutureWeatherAdapter.FutureViewHolder>() {

    fun updateWeatherList(newWeatherList: List<com.rahul.weatherapp.model.List>) {
        weatherList.clear()
        weatherList.addAll(newWeatherList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.future_card_view, parent, false)
        return FutureViewHolder(view)
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: FutureViewHolder, position: Int) {
        holder.date.text = weatherList[position].dtTxt

        holder.minTemp.text = weatherList[position].main?.tempMin?.let { minTemp ->
            Util.convertKelvinToCelsius(minTemp).toString()
        }

        holder.maxTemp.text = weatherList[position].main?.tempMax?.let { maxTemp ->
            Util.convertKelvinToCelsius(maxTemp).toString()
        }

        when (weatherList[position].weather[0].main.toString()) {
            "Rain" -> {
                holder.imageView.setImageResource(R.drawable.ic_rain)
            }
            "Clouds" -> {
                holder.imageView.setImageResource(R.drawable.ic_clound)
            }
            "Clear" -> {
                holder.imageView.setImageResource(R.drawable.ic_sun)
            }
        }
    }

    class FutureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.rc_iv_weather_type)
        val date: TextView = itemView.findViewById(R.id.tv_date_time)
        val maxTemp: TextView = itemView.findViewById(R.id.rc_tv_max_temp)
        val minTemp: TextView = itemView.findViewById(R.id.rc_tv_min_temp)
    }
}