package com.arjun1194.nativeweather.utils

import android.content.Context
import android.widget.Toast
import com.arjun1194.nativeweather.MyApplication
import dagger.hilt.android.qualifiers.ApplicationContext

fun errorToast(context: Context){
    Toast.makeText(context,Constants.LOCATION_PERMISSION_DENIED_MESSAGE, Toast.LENGTH_LONG).show()
}