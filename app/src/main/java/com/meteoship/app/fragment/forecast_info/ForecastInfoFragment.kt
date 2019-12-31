package com.meteoship.app.fragment.forecast_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.meteoship.R
import com.meteoship.base.BaseFragment
import com.meteoship.model.data.ForecastInfoDataItem
import kotlinx.android.synthetic.main.fragment_forecast_info.*

class ForecastInfoFragment : BaseFragment(), ForecastInfoView {

    private lateinit var presenter: ForecastInfoPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ForecastInfoPresenter()
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

    override fun showData(data: List<ForecastInfoDataItem>) {
        forecast_info_list.adapter = ForecastInfoAdapter(data)
        forecast_info_list.layoutManager = GridLayoutManager(context!!, 2)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}