package com.luc.meli_job_readiness.ui.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("srcUrl", "circleCrop", "placeholder", requireAll = false)
fun ImageView.bindSrcUrl(
    url: String?,
    circleCrop: Boolean,
    placeholder: Drawable?,
) {
    if (url.isNullOrEmpty()) return
    val request = Glide.with(this).load(url)
    if (circleCrop) request.circleCrop()
    if (placeholder != null) request.placeholder(placeholder)
    request.into(this)
}