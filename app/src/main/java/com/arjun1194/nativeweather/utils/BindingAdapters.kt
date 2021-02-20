package com.arjun1194.nativeweather.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.arjun1194.nativeweather.R
import com.bumptech.glide.Glide

@BindingAdapter("bind:src")
fun bindingImageSrc(view: ImageView, src: String?) {
    src?.let { Glide.with(view).load(it).into(view) }
}

@BindingAdapter("app:iconBinding")
fun bindWeatherImage(view:ImageView, icon: String?){
    if (icon!=null){
        when(icon){
            "01d"-> view.setImageDrawable(R.drawable.sun)
            "02d"-> view.setImageDrawable(R.drawable.cloudy2)
            "03d"-> view.setImageDrawable(R.drawable.cloud3)
            "04d"-> view.setImageDrawable(R.drawable.storm2)
            "09d"-> view.setImageDrawable(R.drawable.raining)
            "10d"-> view.setImageDrawable(R.drawable.umbrella)
            "11d"-> view.setImageDrawable(R.drawable.storm2)
            "13d"-> view.setImageDrawable(R.drawable.wind2)
            "50d"-> view.setImageDrawable(R.drawable.wind)

            "01n"-> view.setImageDrawable(R.drawable.sun)
            "02n"-> view.setImageDrawable(R.drawable.cloudy2)
            "03n"-> view.setImageDrawable(R.drawable.cloud3)
            "04n"-> view.setImageDrawable(R.drawable.storm2)
            "09n"-> view.setImageDrawable(R.drawable.raining)
            "10n"-> view.setImageDrawable(R.drawable.umbrella)
            "11n"-> view.setImageDrawable(R.drawable.storm2)
            "13n"-> view.setImageDrawable(R.drawable.wind2)
            "50n"-> view.setImageDrawable(R.drawable.wind)
        }
    }
}

