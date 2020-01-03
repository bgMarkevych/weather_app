package com.meteoship.app.fragment.prediction

import com.meteoship.WeatherApp
import com.meteoship.base.BasePresenter
import com.meteoship.neural_network.data.BaroTrend
import com.meteoship.neural_network.data.Month
import com.meteoship.neural_network.data.Neuron
import com.meteoship.neural_network.data.Wind

class PredictionPresenter : BasePresenter<PredictionView>() {

    fun predictWeather(
        z_hpa: Double,
        z_month: Month,
        z_wind: Wind,
        z_trend: BaroTrend,
        z_hemisphere: Int,
        z_upper: Double,
        z_lower: Double
    ) {
        val disposable = WeatherApp.component.getModel()
            .predictWeather(Neuron(z_hpa, z_month, z_wind, z_trend, z_hemisphere, z_upper, z_lower))
            .subscribe({ view?.showResult(it) }, this::processError)
    }

}