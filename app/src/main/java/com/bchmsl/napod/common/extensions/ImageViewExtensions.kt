package com.bchmsl.napod.common.extensions

import android.widget.ImageView
import com.bchmsl.napod.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}