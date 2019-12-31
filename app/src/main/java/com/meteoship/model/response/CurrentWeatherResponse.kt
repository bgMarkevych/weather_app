package com.meteoship.model.response

import com.google.gson.annotations.Expose
import com.meteoship.model.data.CurrentWeatherItem

class CurrentWeatherResponse {

    @Expose
    var data: List<CurrentWeatherItem>? = null

}