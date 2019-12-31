package com.meteoship.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meteoship.model.data.DailyWeatherItem
import com.meteoship.model.data.HourlyWeatherItem
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface DailyWeatherItemDao {
    @Query("SELECT * FROM dailyweatheritem")
    fun getAll(): List<DailyWeatherItem>

    @Insert
    fun insert(weather: List<DailyWeatherItem>)

    @Query("DELETE FROM dailyweatheritem")
    fun clearTable()

    @Query("SELECT * FROM dailyweatheritem WHERE validDate LIKE :date LIMIT 1")
    fun get(date: String): DailyWeatherItem

}