package com.meteoship.model.container

import com.meteoship.model.data.CurrentWeatherItem
import com.meteoship.model.data.ForecastInfoDataItem
import com.meteoship.model.data.HourlyWeatherItem

class CurrentWeatherContainer(
    val currentWeatherItem: CurrentWeatherItem,
    val hourlyForecast: List<HourlyWeatherItem>,
    val forecastInfo: List<ForecastInfoDataItem>
)