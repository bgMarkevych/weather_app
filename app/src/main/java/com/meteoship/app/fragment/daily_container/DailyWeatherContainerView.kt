package com.meteoship.app.fragment.daily_container

import com.meteoship.base.BaseView
import com.meteoship.model.data.DailyWeatherContainer

interface DailyWeatherContainerView : BaseView {
    fun showData(container: DailyWeatherContainer)
}