package com.meteoship.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.meteoship.BuildConfig
import com.meteoship.R
import com.meteoship.WeatherApp
import com.meteoship.model.exception.NetworkNotAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.UnknownHostException

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response
        checkNetwork(request)
        try {
            response = chain.proceed(request)
        } catch (ex: UnknownHostException) {
            throw NetworkNotAvailable(WeatherApp.context.getString(R.string.connection_lost_dialog_message))
        }
        return response
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    @Throws(NetworkNotAvailable::class)
    private fun checkNetwork(request: Request) {
        val appContext = WeatherApp.context
        if (!isNetworkAvailable(appContext)) {
            val errorString = appContext.getString(R.string.connection_lost_dialog_message)
            if (BuildConfig.DEBUG) {
                Log.e("NoInet", "URL: " + request.url)
            }
            throw NetworkNotAvailable(errorString)
        }
    }

}
