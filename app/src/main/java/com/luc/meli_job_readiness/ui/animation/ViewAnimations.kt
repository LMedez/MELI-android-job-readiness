package com.luc.meli_job_readiness.ui.animation

import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageButton
import com.google.android.material.animation.AnimationUtils

private fun scaleXY(
    view: View,
    to: Float,
    interpolatorApplied: TimeInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR,
) {
    view.animate().scaleX(to).scaleY(to).apply {
        interpolator = interpolatorApplied
        duration = 300
    }
}

fun ImageButton.startFavAnimation() {
    if (this.scaleX == 0f) scaleXY(this, 1f, OvershootInterpolator(3f))
    else scaleXY(this, 0f, AnticipateInterpolator(3f))
}