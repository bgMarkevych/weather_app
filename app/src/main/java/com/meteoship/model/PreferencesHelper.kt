package com.meteoship.model

import android.content.SharedPreferences

const val LAT_KEY = "LAT_KEY"
const val LON_KEY = "LON_KEY"
const val CITY_KEY = "CITY_KEY"
const val TIME_ZONE_KEY = "TIME_ZONE_KEY"

class PreferencesHelper(private val preferences: SharedPreferences) {

    fun storeCurrentCoordinates(lat: Double, lon: Double) {
        preferences.edit()
            .putFloat(LAT_KEY, lat.toFloat())
            .putFloat(LON_KEY, lon.toFloat())
            .apply()
    }

    fun getCurrentCoordinates(): Pair<Float, Float> {
        return Pair(preferences.getFloat(LAT_KEY, 0f), preferences.getFloat(LON_KEY, 0f))
    }

    fun storeCurrentTimeZone(timezone: String?) {
        preferences.edit()
            .putString(TIME_ZONE_KEY, timezone)
            .apply()
    }

    fun storeCurrentCityName(cityName: String?) {
        preferences.edit()
            .putString(CITY_KEY, cityName)
            .apply()
    }

    fun getCurrentTimeZone(): String? {
        return preferences
            .getString(TIME_ZONE_KEY, null)
    }

    fun getCurrentCityName(): String? {
        return preferences
            .getString(CITY_KEY, null)

    }
}