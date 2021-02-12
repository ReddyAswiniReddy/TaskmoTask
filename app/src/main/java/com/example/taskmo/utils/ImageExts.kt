package com.example.taskmo.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.taskmo.R


fun ImageView.loadImage(source: String?, requestOptions: RequestOptions? = getCircleCenterCropOption(), color:Int= R.color.colorPrimary) {
    source?.let { url ->
        if(url.isRequiredField()) {
            val requestBuilder = Glide.with(context).load(url)
            requestOptions?.let {
                it.placeholder(setProgressDialog(context, color))
                requestBuilder.apply(it)
            }
            requestBuilder.into(this)
        } else{
            setImageResource(0)
        }
    }
}

fun ImageView.loadImageWithPlaceholder(source: String?, requestOptions: RequestOptions? = null,placeHolder:Int){
    source?.let { url ->
        val requestBuilder = Glide.with(context).load(url)
        requestOptions?.let {
            requestBuilder.apply(it)
            it.placeholder(placeHolder)
        }
        requestBuilder.into(this)
    }
}

fun ImageView.addRoundedCorners(source: String?,radius:Int=25) {
    source?.let { url ->
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(radius))
        requestOptions.placeholder(setProgressDialog(context))
        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this)
    }
}

fun ImageView.addRoundedCornersWithPlaceHolders(source: String?,radius:Int = 25) {
    source?.let { url ->
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(radius))
        requestOptions.placeholder(R.drawable.bg_gray_placeholder_rounded)
        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this)
    }
}

fun getCircleCenterCropOption(): RequestOptions {
    return RequestOptions()
//            .placeholder(R.drawable.rounded_gray_glide_placeholder)
        .transform(CenterCrop(), CircleCrop())
}

fun setProgressDialog(context: Context,color:Int= R.color.colorPrimary): CircularProgressDrawable {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 26f
    circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context, color))
    circularProgressDrawable.start()
    return circularProgressDrawable
}

