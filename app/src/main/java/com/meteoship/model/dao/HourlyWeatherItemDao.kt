package com.meteoship.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meteoship.model.data.HourlyWeatherItem
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface HourlyWeatherItemDao {
    @Query("SELECT * FROM hourlyweatheritem")
    fun getAll(): Observable<List<HourlyWeatherItem>>

    @Insert
    fun insert(weather: List<HourlyWeatherItem>)

    @Query("DELETE FROM hourlyweatheritem")
    fun clearTable()

}