package com.luc.meli_job_readiness.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.luc.meli_job_readiness.R

/**
 * Bind any ImageView with these properties for load images url with Glide
 */
@BindingAdapter("srcUrl", "circleCrop", requireAll = false)
fun ImageView.bindSrcUrl(
    url: String?,
    circleCrop: Boolean,
) {
    if (url.isNullOrEmpty()) return
    val request = Glide.with(this).load(url)
    request.transition(DrawableTransitionOptions.withCrossFade())
    if (circleCrop) request.circleCrop()
    request.placeholder(context.getDrawable(R.mipmap.ic_logo_foreground))
    request.into(this)
}