package com.meteoship.model

import com.meteoship.R
import com.meteoship.WeatherApp
import com.meteoship.model.data.*
import com.meteoship.model.response.DailyWeatherResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

const val DAYS_TO_LOAD = 5
const val HOURS = 24

class Model(private val apiInterface: ApiInterface, private val storageManager: StorageManager) {

    private fun getApiKey(): String {
        return WeatherApp.context.getString(R.string.api_key)
    }

    fun loadCurrentForecast(
        latitude: Double,
        longitude: Double
    ): Observable<DailyWeatherResponse> {
        return apiInterface.getForecastByCoordinates(
            latitude,
            longitude,
            getApiKey()
        )
            .doOnNext { storageManager.clearCurrentWeatherData() }
            .map { it.data!![0] }
            .doOnNext { storageManager.storeCurrentCoordinates(it.lat!!, it.lon!!) }
            .doOnNext { storageManager.saveCurrentWeather(it) }
            .flatMap {
                loadHourlyForecast(
                    latitude,
                    longitude
                ).concatMap { loadDailyForecast(latitude, longitude) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun loadHourlyForecast(
        latitude: Double,
        longitude: Double
    ): Observable<List<HourlyWeatherItem>> {
        return Observable.fromCallable {
            val calendar = Calendar.getInstance()
            calendar.timeZone =
                TimeZone.getTimeZone(storageManager.getCurrentWeather().timezone)
            return@fromCallable HOURS - calendar.get(Calendar.HOUR_OF_DAY)
        }
            .flatMap {
                apiInterface.getHourlyForecastByCoordinates(
                    latitude,
                    longitude,
                    getApiKey(),
                    it
                )
            }
            .map {
                it.data!!.forEach { item ->
                    item.lat = it.lat
                    item.lon = it.lon
                }
                return@map it.data!!
            }
            .doOnNext { storageManager.saveHourlyWeatherForecast(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun loadDailyForecast(
        latitude: Double,
        longitude: Double
    ): Observable<DailyWeatherResponse> {
        return apiInterface.getDailyForecastByCoordinates(
            latitude,
            longitude,
            getApiKey(),
            DAYS_TO_LOAD
        )
            .doOnNext { storageManager.saveDailyWeatherForecast(it.data!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCurrentWeather(): Observable<CurrentWeatherItem> {
        return Observable.fromCallable {  storageManager.getCurrentWeather()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCurrentForecastInfo(): Observable<ArrayList<ForecastInfoDataItem>> {
        return Observable.fromCallable {  storageManager.getCurrentWeather()}
            .map { item ->
                val forecastInfo = ArrayList<ForecastInfoDataItem>()
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.temperature_max,
                        (item.temp!! + 3).toInt().toString(),
                        R.color.max_temp_text_color
                    )
                )
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.pressure,
                        item.pres.toString() + " m/b"
                    )
                )
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.temperature_min,
                        (item.temp!! - 3).toInt().toString(),
                        R.color.min_temp_text_color
                    )
                )
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.wind_speed,
                        item.windSpd.toString() + " m/s"
                    )
                )
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.temperature_feels_like,
                        item.appTemp!!.toInt().toString()
                    )
                )
                forecastInfo.add(
                    ForecastInfoDataItem(
                        R.string.relative_humidity,
                        item.rh.toString() + "%"
                    )
                )
                return@map forecastInfo
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun refreshCurrentWeather(): Observable<CurrentWeatherItem> {
        return loadCurrentForecast(
            storageManager.getCurrentCoordinates().first.toDouble(),
            storageManager.getCurrentCoordinates().second.toDouble()
        )
            .flatMap { getCurrentWeather() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHourlyForecast(): Observable<ArrayList<HourWeatherDataItem>> {
        return storageManager.getHourlyWeatherForecast()
            .map {
                val newList = ArrayList<HourWeatherDataItem>()
                it.forEach {
                    newList.add(
                        HourWeatherDataItem(
                            WeatherState.getWeatherStateById(it.weather!!.code!!),
                            it.temp!!.toInt().toString(),
                            it.timestampLocal!!,
                            it.isNight()
                        )
                    )
                }
                return@map newList
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCurrentCoordinates(): Pair<Float, Float> {
        return storageManager.getCurrentCoordinates()
    }

    fun getCurrentCoordinatesObservable(): Observable<Pair<Float, Float>> {
        return Observable.just(storageManager.getCurrentCoordinates())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}