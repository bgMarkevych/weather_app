package com.meteoship.app.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import com.meteoship.R
import com.meteoship.app.fragment.current_weather.CurrentWeatherFragment
import com.meteoship.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.container,
                CurrentWeatherFragment(), CurrentWeatherFragment::class.java.canonicalName
            )
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun dialogClick(dialogId: Int, data: Any?) {

    }
}
