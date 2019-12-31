package com.meteoship.app.activity.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.meteoship.R
import com.meteoship.app.activity.main.MainActivity
import com.meteoship.base.BaseActivity
import com.meteoship.utils.PermissionRequestHandler
import java.lang.Exception


const val PERMISSION_REQUEST_CODE = 1998

class SplashActivity : BaseActivity(), SplashView, LocationListener {

    private val permissionRequestHandler = PermissionRequestHandler()
    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter()
        presenter.attach(this)
        permissionRequestHandler.startPermissionsRequestFlow(
            this,
            PERMISSION_REQUEST_CODE,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            {
                loadCurrentLocationWeather()
            },
            {
                showGoToSettingsDialog("location")
            }
        )
    }

    @SuppressLint("MissingPermission")
    private fun loadCurrentLocationWeather() {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            presenter.onCreate(location.latitude, location.longitude)
        } catch (e: Exception) {
            e.printStackTrace()
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L,
                500.0f,
                this
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionRequestHandler.handlePermissionRequestResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onLoadFinished() {
        if (isFinishing) {
            return
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            presenter.onCreate(location.latitude, location.longitude)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

}
