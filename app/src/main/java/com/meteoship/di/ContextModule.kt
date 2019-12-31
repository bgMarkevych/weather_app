package com.meteoship.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideContext(): Context {
        return context
    }

}