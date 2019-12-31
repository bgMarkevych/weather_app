package com.meteoship.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.meteoship.WeatherApp

class PermissionRequestHandler {

    private lateinit var actionGranted: () -> Unit
    private lateinit var actionDenied: () -> Unit
    private var requestCode: Int = 0

    fun startPermissionsRequestFlow(
        activity: Activity,
        requestCode: Int,
        permissions: Array<out String>,
        actionGranted: () -> Unit,
        actionDenied: () -> Unit
    ) {
        this.requestCode = requestCode
        this.actionDenied = actionDenied
        this.actionGranted = actionGranted
        if (!checkPermissions(permissions)) {
            requestPermissions(activity, permissions)
        } else {
            actionGranted.invoke()
        }
    }

    private fun checkPermissions(permissions: Array<out String>): Boolean {
        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(
                    WeatherApp.context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    private fun requestPermissions(activity: Activity, permissions: Array<out String>) {
        ActivityCompat.requestPermissions(
            activity,
            permissions,
            requestCode
        )
    }

    fun handlePermissionRequestResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != this.requestCode) {
            return
        }
        var granted = true
        for (element in grantResults) {
            if (element == PackageManager.PERMISSION_DENIED) {
                granted = false
                break
            }
        }
        if (!granted) {
            actionDenied.invoke()
        } else {
            actionGranted.invoke()
        }
    }

}