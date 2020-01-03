package com.meteoship.di

import com.meteoship.neural_network.algorithm.Zambretti
import com.meteoship.neural_network.algorithm.ZambrettiImpl
import dagger.Module
import dagger.Provides

@Module
class NeuralNetworkModule {

    @AppScope
    @Provides
    fun provideZambrettiNeuralNetwork(): Zambretti {
        return ZambrettiImpl()
    }

}