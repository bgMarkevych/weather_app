package com.meteoship.app.activity.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.meteoship.R
import com.meteoship.app.fragment.current_weather.CurrentWeatherFragment
import com.meteoship.app.fragment.daily_container.DailyWeatherContainerFragment
import com.meteoship.base.BaseActivity
import com.meteoship.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_menu.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initFragment(CurrentWeatherFragment())
        }
        bottom_menu.itemIconTintList = null
        bottom_menu.setOnNavigationItemSelectedListener {
            manageBottomMenuClicks(it)
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun manageBottomMenuClicks(item: MenuItem) {
        Log.d("MENU", item.title.toString())
        when (item.itemId) {
            R.id.current_weather -> initFragment(CurrentWeatherFragment())
            R.id.range_weather -> initFragment(DailyWeatherContainerFragment())
//            R.id.prediction -> initFragment()
        }
    }

    private fun initFragment(fragment: BaseFragment) {
        if (supportFragmentManager.findFragmentByTag(fragment::class.java.canonicalName) != null) {
            return
        }
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                fragment, fragment::class.java.canonicalName
            )
            .commitAllowingStateLoss()
    }

    override fun dialogClick(dialogId: Int, data: Any?) {

    }
}
