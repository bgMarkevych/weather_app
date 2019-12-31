package com.meteoship.model

import com.meteoship.model.data.HourlyWeatherItem
import com.meteoship.model.data.CurrentWeatherItem
import com.meteoship.model.data.DailyWeatherItem
import io.reactivex.Completable
import io.reactivex.Observable

class StorageManager(
    private val preferencesHelper: PreferencesHelper,
    private val database: Database
) {

    fun saveCurrentWeather(currentWeather: CurrentWeatherItem) {
        database.currentWeatherDao().insert(currentWeather)
    }

    fun saveHourlyWeatherForecast(forecast: List<HourlyWeatherItem>) {
        database.hourlyWeatherDao().insert(forecast)
    }

    fun saveDailyWeatherForecast(forecast: List<DailyWeatherItem>) {
        database.dailyWeatherDao().insert(forecast)
    }

    fun getDailyWeatherForecast(): List<DailyWeatherItem> {
        return database.dailyWeatherDao().getAll()
    }

    fun getSingleDailyWeatherForecast(date: String): DailyWeatherItem {
        return database.dailyWeatherDao().get(date)
    }

    fun getCurrentWeather(): CurrentWeatherItem? {
        return database.currentWeatherDao().getCurrent()
    }

    fun getHourlyWeatherForecast(): Observable<List<HourlyWeatherItem>> {
        return database.hourlyWeatherDao().getAll()
    }

    fun storeCurrentCoordinates(lat: Double, lon: Double) {
        preferencesHelper.storeCurrentCoordinates(lat, lon)
    }

    fun getCurrentCoordinates(): Pair<Float, Float> {
        return preferencesHelper.getCurrentCoordinates()
    }

    fun clearCurrentWeatherData() {
        database.currentWeatherDao().clearTable()
        database.dailyWeatherDao().clearTable()
        database.hourlyWeatherDao().clearTable()
    }

    fun storeCurrentTimeZone(timezone: String?) {
        preferencesHelper.storeCurrentTimeZone(timezone)
    }

    fun storeCurrentCityName(cityName: String?) {
        preferencesHelper.storeCurrentCityName(cityName)
    }

    fun getCurrentTimeZone(): String? {
        return preferencesHelper.getCurrentTimeZone()
    }

    fun getCurrentCityName(): String? {
        return preferencesHelper.getCurrentCityName()
    }
}