package com.meteoship.app.fragment.daily_weather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.meteoship.R
import com.meteoship.app.fragment.forecast_info.ForecastInfoAdapter
import com.meteoship.base.BaseFragment
import com.meteoship.model.data.DailyWeatherItem
import com.meteoship.model.data.ForecastInfoDataItem
import com.meteoship.model.data.SheepIcon
import com.meteoship.model.data.WeatherState
import com.meteoship.utils.changeStatusBarColor
import kotlinx.android.synthetic.main.fragment_daily_weather.sunrise
import kotlinx.android.synthetic.main.fragment_daily_weather.sunset
import kotlinx.android.synthetic.main.fragment_daily_weather.temp
import kotlinx.android.synthetic.main.fragment_daily_weather.weather_background
import kotlinx.android.synthetic.main.fragment_daily_weather.weather_date
import kotlinx.android.synthetic.main.fragment_forecast_info.*
import java.util.*

const val DATE_KEY = "DATE_KEY"

class DailyWeatherFragment : BaseFragment(), DailyWeatherView {

    companion object {
        fun newInstance(date: String): Fragment {
            val fragment = DailyWeatherFragment()
            val bundle = Bundle()
            bundle.putString(DATE_KEY, date)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: DailyWeatherPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = DailyWeatherPresenter()
        presenter.attach(this)
        presenter.getData(arguments!!.getString(DATE_KEY)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_weather, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun showData(forecast: DailyWeatherItem) {
        TransitionManager.beginDelayedTransition(view!! as ViewGroup)
        weather_date.setDate(forecast.validDate!!, DailyWeatherItem.DATE_FORMAT)
        sunrise.text = "${getCorrectTime(
            forecast.sunriseTs!!.toLong(),
            Calendar.HOUR_OF_DAY
        )}:${getCorrectTime(forecast.sunriseTs!!.toLong(), Calendar.MINUTE)}"
        sunset.text =
            "${getCorrectTime(forecast.sunsetTs!!.toLong(), Calendar.HOUR_OF_DAY)}:${getCorrectTime(
                forecast.sunriseTs!!.toLong(),
                Calendar.MINUTE
            )}"
        val weatherState =
            WeatherState.getWeatherStateById(forecast.weather!!.code!!)
        temp.setCompoundDrawablesWithIntrinsicBounds(
            weatherState.drawableId,
            0,
            0,
            0
        )
        temp.text = "${forecast.temp!!.toInt()}°"
        weather_background.setColorBackground(
            ContextCompat.getColor(
                context!!,
                weatherState.weatherColor
            )
        )
        weather_background.setImageResource(SheepIcon.getIconByTemp(forecast.temp!!.toInt()))
//        changeStatusBarColor(activity!!, weatherState.weatherColor)

        forecast_info_list.adapter = ForecastInfoAdapter(buildForestInfoList(forecast))
        forecast_info_list.layoutManager = GridLayoutManager(context!!, 2)
    }

    private fun buildForestInfoList(forecast: DailyWeatherItem): List<ForecastInfoDataItem> {
        val forecastInfo = ArrayList<ForecastInfoDataItem>()
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.temperature_max,
                forecast.maxTemp!!.toInt().toString(),
                R.color.max_temp_text_color
            )
        )
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.pressure,
                forecast.pres.toString() + " m/b"
            )
        )
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.temperature_min,
                forecast.minTemp!!.toInt().toString(),
                R.color.min_temp_text_color
            )
        )
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.wind_speed,
                forecast.windSpd.toString() + " m/s"
            )
        )
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.temperature_feels_like,
                ((forecast.maxTemp!! + forecast.minTemp!!) / 2).toInt().toString()
            )
        )
        forecastInfo.add(
            ForecastInfoDataItem(
                R.string.relative_humidity,
                forecast.rh.toString() + "%"
            )
        )
        return forecastInfo
    }

    private fun getCorrectTime(milliseconds: Long, type: Int): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        var time = calendar.get(type).toString()
        if (time.length == 1) {
            time = "0${time}"
        }
        return time
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}