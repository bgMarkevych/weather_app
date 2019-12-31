package com.meteoship.model

import com.meteoship.model.response.CurrentWeatherResponse
import com.meteoship.model.response.DailyWeatherResponse
import com.meteoship.model.response.HourlyWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("/v2.0/current")
    fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String
    ): Observable<CurrentWeatherResponse>

    @GET("/v2.0/forecast/hourly")
    fun getHourlyForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String,
        @Query("hours") hours: Int
    ): Observable<HourlyWeatherResponse>

    @GET("/v2.0/forecast/daily")
    fun getDailyForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String,
        @Query("days") days: Int
    ): Observable<DailyWeatherResponse>



}