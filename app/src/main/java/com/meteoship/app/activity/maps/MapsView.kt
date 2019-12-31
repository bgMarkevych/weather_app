package com.meteoship.app.activity.maps

import com.meteoship.base.BaseView

interface MapsView: BaseView {
    fun loadCoordinates(lat: Double, lon: Double)
}