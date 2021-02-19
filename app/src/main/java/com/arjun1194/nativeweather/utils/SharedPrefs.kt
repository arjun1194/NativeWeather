package com.arjun1194.nativeweather.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    var timestamp: String
        get() = sharedPreferences.getString(timeStampValue, "")!!
        set(value) {
            sharedPreferences.edit().putString(timeStampValue, value).apply()
        }

    companion object {
        private const val timeStampValue = "timestamp"
    }

}
