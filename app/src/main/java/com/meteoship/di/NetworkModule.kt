package com.meteoship.di

import com.meteoship.model.ApiInterface
import com.meteoship.utils.ResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_MILLIS: Long = 20

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.weatherbit.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(responseInterceptor)
            connectTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
        }.build()
    }

    @AppScope
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().setLevel(level)
    }

    @AppScope
    @Provides
    fun provideResponseInterceptor(): ResponseInterceptor {
        return ResponseInterceptor()
    }

}