package com.meteoship.app.fragment.daily_weather

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class DailyWeatherPresenter : BasePresenter<DailyWeatherView>() {

    fun getData(date: String) {
        val disposable = WeatherApp.component.getModel().getSingleDailyForecast(date)
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

}