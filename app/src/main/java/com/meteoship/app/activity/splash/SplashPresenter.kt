package com.meteoship.app.activity.splash

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter
import com.meteoship.model.Model
import com.meteoship.model.exception.NetworkNotAvailable
import com.meteoship.model.response.DailyWeatherResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function

class SplashPresenter : BasePresenter<SplashView>() {

    private val model: Model = WeatherApp.component.getModel()

    fun onCreate(latitude: Double, longitude: Double) {
        val disposable = model.loadCurrentForecast(latitude, longitude)
            .onErrorResumeNext(Function { t ->
                if (t is NetworkNotAvailable) {
                    return@Function model.isCurrentWeatherNull()
                        .map {
                            if (!it) {
                                return@map DailyWeatherResponse()
                            } else {
                                throw t
                            }
                        }
                }
                return@Function Observable.error(t)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view?.onLoadFinished() }, { processError(it) })
        compositeDisposable.add(disposable)
    }
}