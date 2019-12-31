package com.meteoship.app.fragment.current_weather

import android.util.Log
import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class CurrentWeatherPresenter : BasePresenter<CurrentWeatherView>() {

    val model = WeatherApp.component.getModel()

    fun getData() {
        val disposable = model.getCurrentWeather()
            .doOnSubscribe { view?.showLoading() }
            .doFinally { view?.dismissLoading() }
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

    fun refreshData() {
        val disposable = model.refreshCurrentWeather()
            .doOnSubscribe { view?.showLoading() }
            .doFinally { view?.dismissLoading() }
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

    fun loadNewLocation(lat: Double, lon: Double) {
        val disposable =
            model.loadCurrentForecast(latitude = lat, longitude = lon)
                .flatMap { model.getCurrentWeather() }
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.dismissLoading() }
                .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

}