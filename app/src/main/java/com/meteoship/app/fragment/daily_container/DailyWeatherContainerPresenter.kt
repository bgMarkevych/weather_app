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

}