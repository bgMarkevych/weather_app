package com.meteoship.app.fragment.hourly_forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.meteoship.R
import com.meteoship.model.data.CurrentWeatherItem
import com.meteoship.model.data.HourWeatherDataItem
import com.meteoship.model.data.WeatherState
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class HourlyForecastAdapter(private val data: List<HourWeatherDataItem>) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    private val formatter = SimpleDateFormat(CurrentWeatherItem.dateFormat, Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        return HourlyForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hour_forecast_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        val item = data[position]
        holder.temp.text = item.temp
        if (item.isNight) {
            holder.temp.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.text_color_night
                )
            )
        } else {
            holder.temp.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.text_color_day
                )
            )
        }
        val drawableId = if (item.weatherState == WeatherState.SUNNY) {
            if (item.isNight) {
                R.drawable.ic_moon
            } else {
                R.drawable.ic_sun_46x46
            }
        } else {
            item.weatherState.drawableId
        }
        holder.icon.setImageResource(drawableId)
        var date = item.time
        if (date.contains("T")) {
            date = date.replace("T", " ")
        }
        try {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = formatter.parse(date).time
            var hour = calendar.get(Calendar.HOUR_OF_DAY).toString()
            if (hour.length == 1) {
                hour = "0$hour"
            }
            var minutes = calendar.get(Calendar.MINUTE).toString()
            if (minutes.length == 1) {
                minutes = "0$minutes"
            }
            date = "${hour}:${minutes}"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        holder.time.text = date
    }


    inner class HourlyForecastViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {
            temp = itemView.findViewById(R.id.temp)
            icon = itemView.findViewById(R.id.icon)
            time = itemView.findViewById(R.id.time)
        }

        var temp: TextView
        var icon: ImageView
        var time: TextView

    }

}