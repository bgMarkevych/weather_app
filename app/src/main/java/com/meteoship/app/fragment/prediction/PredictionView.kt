package com.meteoship.app.fragment.prediction

import com.meteoship.base.BaseView

interface PredictionView : BaseView {
    fun showResult(result: String)
}