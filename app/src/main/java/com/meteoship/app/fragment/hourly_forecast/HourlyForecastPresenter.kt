package com.meteoship.app.fragment.hourly_forecast

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class HourlyForecastPresenter : BasePresenter<HourlyForecastView>() {

    val model = WeatherApp.component.getModel()

    fun onCreate() {
        val disposable = model.getHourlyForecast()
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

}