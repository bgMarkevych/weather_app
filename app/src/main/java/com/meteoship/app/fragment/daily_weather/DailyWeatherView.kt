package com.meteoship.app.fragment.daily_weather

import com.meteoship.base.BaseView
import com.meteoship.model.data.DailyWeatherItem

interface DailyWeatherView : BaseView {

    fun showData(forecast: DailyWeatherItem)

}