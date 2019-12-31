package com.meteoship.model

import android.content.SharedPreferences

const val LAT_KEY = "LAT_KEY"
const val LON_KEY = "LON_KEY"

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
}