package com.meteoship.app.fragment.daily_container

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.meteoship.R
import com.meteoship.app.activity.maps.LAT_KEY
import com.meteoship.app.activity.maps.LON_KEY
import com.meteoship.app.activity.maps.MapsActivity
import com.meteoship.app.fragment.current_weather.MAPS_REQUEST_CODE
import com.meteoship.app.fragment.daily_weather.DailyWeatherFragment
import com.meteoship.base.BaseFragment
import com.meteoship.model.data.DailyWeatherContainer
import com.meteoship.utils.DefaultViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_daily_weather_container.*
import kotlinx.android.synthetic.main.fragment_daily_weather_container.city
import kotlinx.android.synthetic.main.fragment_daily_weather_container.refresh
import kotlinx.android.synthetic.main.fragment_daily_weather_container.view_pager
import kotlinx.android.synthetic.main.fragment_weather.*

class DailyWeatherContainerFragment : BaseFragment(), DailyWeatherContainerView {

    private lateinit var presenter: DailyWeatherContainerPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = DailyWeatherContainerPresenter()
        presenter.attach(this)
        presenter.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_weather_container, container, false)
    }

    override fun showData(container: DailyWeatherContainer) {
        val fragments = ArrayList<Fragment>()
        container.dates.forEach {
            fragments.add(DailyWeatherFragment.newInstance(it))
        }
        refresh.setOnClickListener { presenter.refreshData() }
        city.text = container.cityName
        city.setOnClickListener {
            startActivityForResult(Intent(context, MapsActivity::class.java), MAPS_REQUEST_CODE)
        }
        view_pager.adapter = DefaultViewPagerAdapter(fragments, childFragmentManager)
    }

    private fun getFrgamentFromViewPager(position: Int): Fragment? {
        return childFragmentManager.findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + position)
    }

    override fun showLoading() {
        refresh.startAnimation(AnimationUtils.loadAnimation(context!!, R.anim.view_rotation))
    }

    override fun dismissLoading() {
        refresh.clearAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MAPS_REQUEST_CODE) {
            presenter.loadNewForecast(
                data!!.extras!!.getDouble(LAT_KEY), data.extras!!.getDouble(LON_KEY)
            )
        }
    }

}