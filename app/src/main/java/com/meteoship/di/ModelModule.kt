package com.meteoship.di

import com.meteoship.model.ApiInterface
import com.meteoship.model.Model
import com.meteoship.model.StorageManager
import dagger.Module
import dagger.Provides

@Module
class ModelModule {

    @AppScope
    @Provides
    fun provideModel(apiInterface: ApiInterface, storageManager: StorageManager): Model {
        return Model(apiInterface, storageManager)
    }

}