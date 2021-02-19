package com.arjun1194.nativeweather.data.model

import com.google.gson.annotations.SerializedName


data class WeatherResource(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val visibility: Double,
    @SerializedName("dt")
    val timestamp: Long,
    val name: String
)