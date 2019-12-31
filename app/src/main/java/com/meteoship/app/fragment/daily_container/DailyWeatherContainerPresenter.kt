package com.meteoship.app.fragment.daily_container

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class DailyWeatherContainerPresenter : BasePresenter<DailyWeatherContainerView>() {

    val model = WeatherApp.component.getModel()

    fun onCreate() {
        val disposable = model.getDailyForecast()
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

    fun refreshData() {
        val disposable = model.refreshCurrentWeather()
            .doOnSubscribe { view?.showLoading() }
            .doFinally { view?.dismissLoading() }
            .flatMap { model.getDailyForecast() }
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

    fun loadNewForecast(
        latitude: Double,
        longitude: Double
    ) {
        val disposable = model.loadCurrentForecast(latitude, longitude)
            .flatMap { model.getDailyForecast() }
            .doOnSubscribe { view?.showLoading() }
            .doFinally { view?.dismissLoading() }
            .flatMap { model.getDailyForecast() }
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

}