package com.meteoship.app.fragment.current_weather

import com.meteoship.base.BaseView
import com.meteoship.model.container.CurrentWeatherContainer
import com.meteoship.model.data.CurrentWeatherItem

interface CurrentWeatherView : BaseView {
    fun showData(weatherContainer: CurrentWeatherItem)
}