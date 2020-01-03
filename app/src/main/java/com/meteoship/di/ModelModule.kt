package com.meteoship.di

import com.meteoship.model.ApiInterface
import com.meteoship.model.Model
import com.meteoship.model.StorageManager
import com.meteoship.neural_network.algorithm.Zambretti
import com.meteoship.neural_network.algorithm.ZambrettiImpl
import dagger.Module
import dagger.Provides

@Module
class ModelModule {

    @AppScope
    @Provides
    fun provideModel(
        apiInterface: ApiInterface,
        storageManager: StorageManager,
        neuralNetwork: Zambretti
    ): Model {
        return Model(apiInterface, storageManager, neuralNetwork)
    }

}