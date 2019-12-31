package com.meteoship

import android.app.Application
import android.content.Context
import com.meteoship.di.AppComponent
import com.meteoship.di.ContextModule
import com.meteoship.di.DaggerAppComponent

class WeatherApp : Application() {

    companion object {
        lateinit var context: Context
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

}