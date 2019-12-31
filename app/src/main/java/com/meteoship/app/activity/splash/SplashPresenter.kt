package com.meteoship.app.activity.splash

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter
import com.meteoship.model.Model
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter : BasePresenter<SplashView>() {

    private val model: Model = WeatherApp.component.getModel()

    fun onCreate(latitude: Double, longitude: Double) {
        val disposable = model.loadCurrentForecast(latitude, longitude)
            .subscribe({ view?.onLoadFinished() }, { processError(it) })
        compositeDisposable.add(disposable)
    }
}