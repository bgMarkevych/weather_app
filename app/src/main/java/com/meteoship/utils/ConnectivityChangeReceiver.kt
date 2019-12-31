package com.meteoship.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo

import io.reactivex.functions.Action

class ConnectivityChangeReceiver : BroadcastReceiver {

    private var networkStateChanged: NetworkChangedListener? = null
    private var isConnectedAction: Action? = null
    private var isDisconnectedAction: Action? = null

    constructor(networkStateChanged: NetworkChangedListener) {
        this.networkStateChanged = networkStateChanged
    }

    constructor(isConnectedAction: Action, isDisconnectedAction: Action) {
        this.isConnectedAction = isConnectedAction
        this.isDisconnectedAction = isDisconnectedAction
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (networkStateChanged != null) {
            networkStateChanged!!.onNetworkStateChanged()
            return
        }

        if (isConnectedAction != null && isDisconnectedAction != null) {
            try {
                if (isConnected(context)) {
                    isConnectedAction!!.run()
                } else {
                    isDisconnectedAction!!.run()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
        return false
    }

    interface NetworkChangedListener {
        fun onNetworkStateChanged()
    }

    companion object {

        val intentFiler: IntentFilter
            get() = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }
}