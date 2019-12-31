package com.meteoship.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meteoship.model.dao.CurrentWeatherItemDao
import com.meteoship.model.dao.DailyWeatherItemDao
import com.meteoship.model.dao.HourlyWeatherItemDao
import com.meteoship.model.data.HourlyWeatherItem
import com.meteoship.model.data.CurrentWeatherItem
import com.meteoship.model.data.DailyWeatherItem

@Database(
    entities = [CurrentWeatherItem::class, HourlyWeatherItem::class, DailyWeatherItem::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherItemDao
    abstract fun hourlyWeatherDao(): HourlyWeatherItemDao
    abstract fun dailyWeatherDao(): DailyWeatherItemDao
}