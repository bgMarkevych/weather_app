package com.meteoship.app.fragment.forecast_info

import com.meteoship.base.BaseView
import com.meteoship.model.data.ForecastInfoDataItem

interface ForecastInfoView: BaseView {

    fun showData(data: List<ForecastInfoDataItem>)

}