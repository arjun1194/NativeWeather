package com.arjun1194.nativeweather.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun ImageView.setImageDrawable(@DrawableRes id: Int){
    this.setImageDrawable(ContextCompat.getDrawable(this.context,id));
}