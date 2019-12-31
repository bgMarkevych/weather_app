package com.meteoship.app.activity.maps

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.meteoship.R
import com.meteoship.base.BaseActivity
import kotlinx.android.synthetic.main.activity_maps.*

const val MAP_VIEW_BUNDLE_KEY = "MAP_VIEW_BUNDLE_KEY"
const val LAT_KEY = "LAT_KEY"
const val LON_KEY = "LON_KEY"

class MapsActivity : BaseActivity(), OnMapReadyCallback, MapsView {

    companion object {
        fun startActivity(context: Activity, requestCode: Int) {
            context.startActivityForResult(Intent(context, MapsActivity::class.java), requestCode)
        }
    }

    private var googleMap: GoogleMap? = null
    private var mapViewBundle: Bundle? = null
    private var currentLatLng: LatLng? = null
    private lateinit var presenter: MapsPresenter
    private var isMapReady: Boolean = false
    private var isLocationSet: Boolean = false
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        setContentView(R.layout.activity_maps)
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        select.setOnClickListener {
            if (currentLatLng == null) {
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra(LAT_KEY, currentLatLng!!.latitude)
            intent.putExtra(LON_KEY, currentLatLng!!.longitude)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        map.onCreate(mapViewBundle)
        map.getMapAsync(this)
        presenter = MapsPresenter()
        presenter.attach(this)
        presenter.getCurrentCoordinates()
    }

    override fun onStart() {
        super.onStart()
        map.onStart()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onMapReady(p0: GoogleMap?) {
        isMapReady = true
        googleMap = p0
        val uiSettings = googleMap?.uiSettings
        uiSettings?.isMyLocationButtonEnabled = true
        uiSettings?.isZoomControlsEnabled = true
        uiSettings?.isRotateGesturesEnabled = true
        uiSettings?.isScrollGesturesEnabled = true
        uiSettings?.isTiltGesturesEnabled = true
        uiSettings?.isZoomGesturesEnabled = true
        googleMap!!.setOnMapClickListener {
            googleMap!!.clear()
            setMarkerOnMap(it.latitude, it.longitude)
            currentLatLng = it
        }
        googleMap!!.setOnMapLongClickListener {
            googleMap!!.clear()
            setMarkerOnMap(it.latitude, it.longitude)
            currentLatLng = it
        }
        if (!isLocationSet) {
            isLocationSet = true
            setMarkerOnMap(lat, lon)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        map.onSaveInstanceState(mapViewBundle)
    }

    override fun loadCoordinates(lat: Double, lon: Double) {
        if (!isMapReady) {
            isLocationSet = false
            this.lat = lat
            this.lon = lon
            return
        }
        setMarkerOnMap(lat, lon)
    }

    private fun setMarkerOnMap(lat: Double, lon: Double) {
        googleMap?.clear()
        val location = LatLng(lat, lon)
        googleMap?.addMarker(MarkerOptions().position(location))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onStop() {
        super.onStop()
        map.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
        presenter.detach()
    }

}
