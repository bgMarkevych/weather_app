package com.meteoship.base

import android.content.BroadcastReceiver
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.content.ContextCompat
import com.meteoship.R
import com.meteoship.app.dialog.ErrorDialog
import com.meteoship.utils.ConnectivityChangeReceiver
import com.meteoship.utils.DialogClickListener

const val CONNECT_STATUS_BANNER_DURATION = 3000
const val CONNECTION_STATE_KEY = "CONNECTION_STATE_KEY"

abstract class BaseActivity : AppCompatActivity(), BaseView, DialogClickListener,
    ConnectivityChangeReceiver.NetworkChangedListener {

    private var connectionStatus: TextView? = null
    protected var rootView: ContentFrameLayout? = null
    private var receiver: BroadcastReceiver? = null
    protected var isConnectionLost: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (receiver == null) {
            receiver = ConnectivityChangeReceiver(this)
        }
        isConnectionLost =
            savedInstanceState != null && savedInstanceState.getBoolean(CONNECTION_STATE_KEY, false)
        rootView = findViewById(android.R.id.content)
        registerReceiver(receiver, ConnectivityChangeReceiver.intentFiler)
    }

    override fun onNetworkStateChanged() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork && activeNetwork.isConnected) {
            if (isConnectionLost) {
                isConnectionLost = false
                onConnectionSuccess()
                Log.d("BASEACTIVITY", "connect")
            }
        } else {
            Log.d("BASEACTIVITY", "lost")
            onConnectionLost()
            isConnectionLost = true
        }
    }

    protected fun onConnectionLost() {
        showConnectionLostBanner()
    }

    protected fun onConnectionSuccess() {
        showConnectionSuccessBanner()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun showErrorDialog(error: String) {
        ErrorDialog.show(this, supportFragmentManager, error)
    }

    protected fun showGoToSettingsDialog(permission: String) {

    }

    override fun dialogClick(dialogId: Int, data: Any?) {

    }

    private fun showConnectionLostBanner() {
        TransitionManager.beginDelayedTransition(rootView)
        if (connectionStatus == null) {
            initBannerView()
        }
        connectionStatus!!.setText(R.string.connection_status_lost)
        connectionStatus!!.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.connection_lost_banner_text_color
            )
        )
        connectionStatus!!.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.connection_lost_banner_background
            )
        )
        connectionStatus!!.setVisibility(View.VISIBLE)
        if (rootView!!.indexOfChild(connectionStatus) != -1) {
            return
        }
        rootView!!.addView(connectionStatus)
        setNextLayoutMargin(resources.getDimensionPixelSize(R.dimen.connection_status_banner_height))
    }

    private fun initBannerView() {
        connectionStatus =
            layoutInflater.inflate(R.layout.connection_status_banner, null, false) as TextView
        connectionStatus!!.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.TOP
        )
        connectionStatus!!.translationZ = 100f
    }

    private fun showConnectionSuccessBanner() {
        if (connectionStatus == null) {
            initBannerView()
            rootView!!.addView(connectionStatus)
        }
        connectionStatus!!.visibility = View.VISIBLE
        connectionStatus!!.setText(R.string.connection_status_success)
        connectionStatus!!.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.connection_success_banner_text_color
            )
        )
        connectionStatus!!.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.connection_success_banner_background
            )
        )
        Handler().postDelayed({
            if (rootView!!.indexOfChild(connectionStatus) == -1) {
                return@postDelayed
            }
            TransitionManager.beginDelayedTransition(rootView)
            connectionStatus!!.visibility = View.GONE
            rootView!!.removeView(connectionStatus)
            setNextLayoutMargin(0)
        }, CONNECT_STATUS_BANNER_DURATION.toLong())
    }

    private fun setNextLayoutMargin(margin: Int) {
        if (rootView!!.childCount == 0) {
            return
        }
        val nextLayout = rootView!!.getChildAt(0)
        if (nextLayout is ViewGroup) {
            nextLayout.setPadding(0, margin, 0, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean(CONNECTION_STATE_KEY, isConnectionLost)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(receiver)
        } catch (e: IllegalArgumentException) {
            onConnectionSuccess()
        }
    }

}