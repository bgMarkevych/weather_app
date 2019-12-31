package com.meteoship.app.activity.maps

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class MapsPresenter : BasePresenter<MapsView>() {

    fun getCurrentCoordinates() {
        val disposable = WeatherApp.component.getModel().getCurrentCoordinatesObservable()
            .subscribe(
                { view?.loadCoordinates(it.first.toDouble(), it.second.toDouble()) },
                this::processError
            )
        compositeDisposable.add(disposable)
    }
}