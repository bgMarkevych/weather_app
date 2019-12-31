package com.meteoship.di

import com.meteoship.model.Model
import dagger.Component

@AppScope
@Component(modules = [ContextModule::class, ModelModule::class, NetworkModule::class, StorageModule::class])
interface AppComponent {
    fun getModel(): Model
}