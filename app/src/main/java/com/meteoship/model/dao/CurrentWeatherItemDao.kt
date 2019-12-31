package com.meteoship.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meteoship.model.data.CurrentWeatherItem
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CurrentWeatherItemDao {
    @Query("SELECT * FROM currentweatheritem")
    fun getCurrent(): CurrentWeatherItem?

    @Insert
    fun insert(weather: CurrentWeatherItem)

    @Query("DELETE FROM currentweatheritem")
    fun clearTable()

}