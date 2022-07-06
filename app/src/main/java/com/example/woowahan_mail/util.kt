package com.example.woowahan_mail

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager

fun Activity.getDeviceWidth(): Float {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    var widthPixel = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        widthPixel = windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        widthPixel = displayMetrics.widthPixels
    }
    val density = resources.displayMetrics.density
    return widthPixel / density
}