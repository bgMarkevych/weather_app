package com.meteoship.app.fragment.hourly_forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meteoship.R
import com.meteoship.base.BaseFragment
import com.meteoship.model.data.HourWeatherDataItem
import kotlinx.android.synthetic.main.fragment_forecast_info.*

class HourlyForecastFragment : BaseFragment(), HourlyForecastView {

    private lateinit var presenter: HourlyForecastPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = HourlyForecastPresenter()
        presenter.attach(this)
        presenter.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_info, container, false)
    }

    override fun showData(data: List<HourWeatherDataItem>) {
        forecast_info_list.adapter = HourlyForecastAdapter(data)
        forecast_info_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}