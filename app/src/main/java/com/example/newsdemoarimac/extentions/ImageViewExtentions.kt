package com.example.newsdemoarimac.extentions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String?, placeholder:Drawable){
    try {
        Glide.with(this.context)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .into(this)
    }catch (e: Exception){
        e.printStackTrace()
    }
}