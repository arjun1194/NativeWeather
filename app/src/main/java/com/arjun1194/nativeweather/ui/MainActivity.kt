package com.arjun1194.nativeweather.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.arjun1194.nativeweather.R
import com.arjun1194.nativeweather.data.model.DataResponse
import com.arjun1194.nativeweather.data.model.WeatherResource
import com.arjun1194.nativeweather.databinding.ActivityMainBinding
import com.arjun1194.nativeweather.ui.main.MainViewModel
import com.arjun1194.nativeweather.utils.NetworkRequestFailedException
import com.arjun1194.nativeweather.utils.errorToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private val viewModel: MainViewModel by viewModels()
    lateinit var cityName: String
    lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        checkAndAskPermissions()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLocation()
                    observeWeather("Delhi")
                } else errorToast(this)
                return
            }
            else -> {}
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
       val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F){
            println(it)
            getCityName(it)
        }

    }


    private fun getCityName(location: Location){
        println("____________________________________________________________ $location")
        val geoCoder = Geocoder(this, Locale.getDefault())
        try {
            val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            val ad= address[0]
            observeWeather("Delhi")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun checkAndAskPermissions(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Granted Location", Toast.LENGTH_SHORT).show()
            getLocation()
            observeWeather("Delhi")
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
    }

    private fun observeWeather(city: String) {
        viewModel.getWeather(city, "metric").observe(this) {
            when (it) {
                is DataResponse.Error -> showError(it.error)
                is DataResponse.Success -> showData(it.data)
            }
        }
    }

    private fun showData(weather: WeatherResource) {
        Log.d(Companion.TAG, "showData: $weather")
        binding.data = weather
    }

    private fun showError(e: Throwable) {
        when (e) {
            is NetworkRequestFailedException -> println(e.message)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_CODE = 1098
    }
}