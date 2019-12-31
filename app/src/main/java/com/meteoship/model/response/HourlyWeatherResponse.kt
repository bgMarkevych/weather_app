package com.meteoship.model.response

import com.google.gson.annotations.Expose
import com.meteoship.model.data.HourlyWeatherItem
import com.google.gson.annotations.SerializedName


class HourlyWeatherResponse {

    @Expose
    var data: List<HourlyWeatherItem>? = null

    @SerializedName("city_name")
    @Expose
    var cityName: String? = null
    @SerializedName("lon")
    @Expose
    var lon: Double? = null
    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null
    @SerializedName("state_code")
    @Expose
    var stateCode: String? = null

}