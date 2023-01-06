package com.firoz.listdemo.api

import android.content.Context
import com.firoz.listdemo.MainApplication
import com.firoz.listdemo.R
import com.firoz.listdemo.utils.Utils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val mContext: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isNetConnected(mContext)) {
            throw NoConnectivityException()
            // Throwing our custom exception 'NoConnectivityException'
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectivityException : IOException() {
        // You can send any message whatever you want from here.
        override val message: String
            get() = MainApplication.appContext!!.getString(R.string.no_internet)

        // You can send any message whatever you want from here.
    }

}
