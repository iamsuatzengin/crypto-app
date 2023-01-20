package com.suatzengin.whataboutcrypto.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter("android:loadImage")
fun loadCoinIcon(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("android:myText")
fun setText(textView: TextView, price: Double){
    textView.text = price.toString().addPrefix("$")
}