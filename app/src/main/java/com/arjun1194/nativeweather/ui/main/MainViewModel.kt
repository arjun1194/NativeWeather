package com.arjun1194.nativeweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun1194.nativeweather.data.model.DataResponse
import com.arjun1194.nativeweather.data.model.WeatherResource
import com.arjun1194.nativeweather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
   private val weatherRepository: WeatherRepository
): ViewModel() {

   private val weather = MutableLiveData<DataResponse<WeatherResource>>()

   fun getWeather(city:String,units: String): LiveData<DataResponse<WeatherResource>>{
      viewModelScope.launch {
         weatherRepository.getWeather(city,units).onEach {
            weather.postValue(it)
         }.launchIn(viewModelScope)
      }
      return weather
   }

}