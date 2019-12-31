package com.meteoship.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.meteoship.model.Database
import com.meteoship.model.PreferencesHelper
import com.meteoship.model.StorageManager
import dagger.Module
import dagger.Provides

const val PREFERENCES_NAME: String = "weather_app"

@Module(includes = [ContextModule::class])
class StorageModule {

    @AppScope
    @Provides
    fun provideStorageManager(
        database: Database,
        preferencesHelper: PreferencesHelper
    ): StorageManager {
        return StorageManager(preferencesHelper, database)
    }

    @AppScope
    @Provides
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "weather_data")
            .fallbackToDestructiveMigrationOnDowngrade()
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun providePreferenceHelper(preferences: SharedPreferences): PreferencesHelper {
        return PreferencesHelper(preferences)
    }

    @AppScope
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

}