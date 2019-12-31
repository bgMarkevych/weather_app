package com.meteoship.utils

import android.app.Activity
import androidx.core.content.ContextCompat

fun changeStatusBarColor(activity: Activity, color: Int) {
    activity.window.statusBarColor = ContextCompat.getColor(activity, color)
}