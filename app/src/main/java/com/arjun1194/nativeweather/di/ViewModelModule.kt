package com.arjun1194.nativeweather.di

import com.arjun1194.nativeweather.api.WeatherService
import com.arjun1194.nativeweather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository {
        return WeatherRepository(weatherService)
    }
}