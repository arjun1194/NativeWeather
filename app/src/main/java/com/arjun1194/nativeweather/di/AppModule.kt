package com.arjun1194.nativeweather.di

import android.content.Context
import com.arjun1194.nativeweather.api.WeatherService
import com.arjun1194.nativeweather.utils.Constants
import com.arjun1194.nativeweather.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideNewsService(): WeatherService {
        return WeatherService.create()
    }


    @Singleton
    @Provides
    fun providesSharedHelper(
        @ApplicationContext context: Context
    ): SharedPrefs {
        val sharedPreferences = context.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        return SharedPrefs(sharedPreferences)
    }

}
