package com.meteoship.app.activity.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.meteoship.R
import com.meteoship.app.fragment.current_weather.CurrentWeatherFragment
import com.meteoship.app.fragment.daily_container.DailyWeatherContainerFragment
import com.meteoship.base.BaseActivity
import com.meteoship.base.BaseFragment
import com.meteoship.utils.DefaultViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_menu.*
import kotlinx.android.synthetic.main.bottom_menu.bottom_menu

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_menu.itemIconTintList = null
        bottom_menu.setOnNavigationItemSelectedListener {
            manageBottomMenuClicks(it)
            return@setOnNavigationItemSelectedListener true
        }
        view_pager.adapter = DefaultViewPagerAdapter(
            listOf(
                CurrentWeatherFragment(),
                DailyWeatherContainerFragment()
            ),
            supportFragmentManager
        )
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottom_menu.selectedItemId =
                    if (position == 0) R.id.current_weather else R.id.range_weather
            }

        })
    }

    private fun manageBottomMenuClicks(item: MenuItem) {
        when (item.itemId) {
            R.id.current_weather -> view_pager.setCurrentItem(0, true)
            R.id.range_weather -> view_pager.setCurrentItem(1, true)
//            R.id.prediction -> initFragment()
        }
    }

    override fun dialogClick(dialogId: Int, data: Any?) {

    }
}
