package com.meteoship.app.fragment.current_weather


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.transition.TransitionManager

import com.meteoship.R
import com.meteoship.app.activity.maps.LAT_KEY
import com.meteoship.app.activity.maps.LON_KEY
import com.meteoship.app.activity.maps.MapsActivity
import com.meteoship.app.fragment.forecast_info.ForecastInfoFragment
import com.meteoship.app.fragment.hourly_forecast.HourlyForecastFragment
import com.meteoship.base.BaseFragment
import com.meteoship.model.data.CurrentWeatherItem
import com.meteoship.model.data.SheepIcon
import com.meteoship.model.data.WeatherState
import com.meteoship.utils.DefaultViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_weather.*
import java.util.*
import com.meteoship.utils.changeStatusBarColor


const val MAPS_REQUEST_CODE = 12

class CurrentWeatherFragment : BaseFragment(),
    CurrentWeatherView {

    private lateinit var presenter: CurrentWeatherPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = CurrentWeatherPresenter()
        presenter.attach(this)
        presenter.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun showData(weatherContainer: CurrentWeatherItem) {
        TransitionManager.beginDelayedTransition(view!! as ViewGroup)
        refresh.setOnClickListener { presenter.refreshData() }
        city.text = weatherContainer.cityName
        city.setOnClickListener {
            startActivityForResult(Intent(context, MapsActivity::class.java), MAPS_REQUEST_CODE)
        }
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone(weatherContainer.timezone)
        weather_date.setDate(
            Date(calendar.timeInMillis)
        )
        sunrise.text = weatherContainer.sunrise!!
        sunset.text = weatherContainer.sunset!!
        val weatherState =
            WeatherState.getWeatherStateById(weatherContainer.weather!!.code!!)
        var drawableId = if (weatherState == WeatherState.SUNNY) {
            if (weatherContainer.isNight()) {
                R.drawable.ic_moon
            } else {
                R.drawable.ic_sun_46x46
            }
        } else {
            weatherState.drawableId
        }
        temp.setCompoundDrawablesWithIntrinsicBounds(
            drawableId,
            0,
            0,
            0
        )
        temp.text = "${weatherContainer.temp!!.toInt()}°"
        val backgroundColor = if (weatherState == WeatherState.SUNNY) {
            if (weatherContainer.isNight()) {
                R.color.night
            } else {
                R.color.sunny
            }
        } else {
            weatherState.weatherColor
        }
        weather_background.color = ContextCompat.getColor(context!!, backgroundColor)
        weather_background.setImageResource(SheepIcon.getIconByTemp(weatherContainer.temp!!.toInt()))
        view_pager.adapter =
            DefaultViewPagerAdapter(
                listOf(ForecastInfoFragment(), HourlyForecastFragment()),
                childFragmentManager
            )
        pager_indicator.selectedColor = ContextCompat.getColor(context!!, backgroundColor)
        changeStatusBarColor(activity!!, backgroundColor)
    }

    override fun showLoading() {
        refresh.startAnimation(AnimationUtils.loadAnimation(context!!, R.anim.view_rotation))
    }

    override fun dismissLoading() {
        refresh.clearAnimation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MAPS_REQUEST_CODE) {
            presenter.loadNewLocation(
                data!!.extras!!.getDouble(LAT_KEY), data.extras!!.getDouble(LON_KEY)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}
