package com.meteoship.app.fragment.forecast_info

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter

class ForecastInfoPresenter : BasePresenter<ForecastInfoView>() {

    val model = WeatherApp.component.getModel()

    fun onCreate() {
        val disposable = model.getCurrentForecastInfo()
            .subscribe({ view?.showData(it) }, this::processError)
        compositeDisposable.add(disposable)
    }

}