package com.arjun1194.nativeweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.arjun1194.nativeweather.R
import com.arjun1194.nativeweather.data.model.DataResponse
import com.arjun1194.nativeweather.data.model.WeatherResource
import com.arjun1194.nativeweather.databinding.ActivityMainBinding
import com.arjun1194.nativeweather.ui.main.MainViewModel
import com.arjun1194.nativeweather.utils.NetworkRequestFailedException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this,R.layout.activity_main)

        observeWeather()
        
    }


    private fun observeWeather(){
        viewModel.getWeather("Noida","metric").observe(this){
            when(it){
                is DataResponse.Error -> showError(it.error)
                is DataResponse.Success -> showData(it.data)
            }
        }
    }

    private fun showData(weather:WeatherResource){
        Log.d(Companion.TAG, "showData: $weather")
        binding.data = weather
    }

    private fun showError(e:Throwable){
        when(e){
            is NetworkRequestFailedException -> println(e.message)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}