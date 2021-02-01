package com.bg.itunesvideo.data.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


/**
 * This loadImage method used to show video thumbnail
 *
 *
 * */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url).into(view)
    }

}

/**
 * This loadRoundedImage method used to show video image in rectangle shape
 *
 *
 * */
@BindingAdapter("roundImage")
fun loadRoundedImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(40))
        val requestOptionsCache = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(view.context).load(url).apply(requestOptions).apply(requestOptionsCache).into(view)
    }



}







