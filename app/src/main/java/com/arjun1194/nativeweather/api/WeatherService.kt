package com.arjun1194.nativeweather.api

import com.arjun1194.nativeweather.data.model.WeatherResource
import com.arjun1194.nativeweather.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("appid") appId: String,
        @Query("q") city: String,
        @Query("units") units: String
    ): WeatherResource


    companion object {
        fun create(): WeatherService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val authInterceptor = Interceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("x-api-key", "ff806b8a914561a8711ea713b3cca098")
                    .build()
                it.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }

}
