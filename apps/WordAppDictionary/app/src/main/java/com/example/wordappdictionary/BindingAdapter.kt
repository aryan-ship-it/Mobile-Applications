package com.example.wordappdictionary

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.d("bindingadapter","bindImage+${imgUrl}")

    imgUrl?.let {
        Log.d("bindingadapter","imgURi")
        val imgUri = imgUrl.toUri().buildUpon().scheme("https://www.merriam-webster.com/assets/mw/static/art/dict/").build()
        Log.d("bindingadapter",imgUri.toString())
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }
    }
}