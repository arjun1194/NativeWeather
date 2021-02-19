package com.arjun1194.nativeweather.data.repository

import com.arjun1194.nativeweather.api.WeatherService
import com.arjun1194.nativeweather.data.model.DataResponse
import com.arjun1194.nativeweather.data.model.WeatherResource
import com.arjun1194.nativeweather.utils.Constants
import com.arjun1194.nativeweather.utils.NetworkRequestFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {

    suspend fun getWeather(city: String,units: String): Flow<DataResponse<WeatherResource>>{
        return flow {
            try {
                val networkResponse = weatherService.getWeather(Constants.API_KEY,city,units);
                println(networkResponse)
                emit(DataResponse.Success(networkResponse))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataResponse.Error(NetworkRequestFailedException(Constants.NETWORK_ERROR_MESSAGE)))
            }
        }.flowOn(Dispatchers.IO)
    }
}