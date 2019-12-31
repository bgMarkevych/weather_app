package com.meteoship.app.fragment.hourly_forecast

import com.meteoship.base.BaseView
import com.meteoship.model.data.HourWeatherDataItem

interface HourlyForecastView: BaseView {
    fun showData(data: List<HourWeatherDataItem>)
}