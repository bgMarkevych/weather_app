package com.meteoship.model.data

import com.meteoship.R

enum class WeatherState(val ids: Array<String>, val drawableId: Int, val weatherColor: Int) {

    FLASH(
        arrayOf("200", "201", "202", "230", "231", "232", "233"),
        R.drawable.ic_flash_46x46,
        R.color.flash
    ),
    RAIN(
        arrayOf("300", "301", "302", "500", "501", "502", "511", "520", "522", "521", "900"),
        R.drawable.ic_rain_46x46,
        R.color.rain
    ),
    SNOW(
        arrayOf("600", "601", "602", "611", "612", "621", "622", "623"),
        R.drawable.ic_snow_46x46,
        R.color.snow
    ),
    SNOW_RAIN(arrayOf("610"), R.drawable.ic_snow_and_rain_46x46, R.color.snow_rain),
    FOG(arrayOf("700", "711", "721", "731", "741", "751"), R.drawable.ic_foggy_46x46, R.color.fog),
    SUNNY(arrayOf("800", "801"), R.drawable.ic_sun_46x46, R.color.sunny),
    CLOUDS(arrayOf("803", "804"), R.drawable.ic_cloud_46x46, R.color.clouds);

    companion object {

        fun getWeatherStateById(id: String): WeatherState {
            values().forEach { weather ->
                weather.ids.forEach {
                    if (it == id) {
                        weather
                    }
                }
            }
            return SUNNY
        }

    }

}