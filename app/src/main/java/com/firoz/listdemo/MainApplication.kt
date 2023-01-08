package com.firoz.listdemo

import android.app.Application
import android.content.Context
import com.firoz.listdemo.api.ApiClient
import com.firoz.listdemo.api.ApiRequests
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplicationContext = this
        // initialize network api client
        ApiClient.init(
            ApiRequests::class.java,
            appContext
        )
    }

    companion object{

        private lateinit var mApplicationContext: MainApplication
        val appContext: Context
            get() = mApplicationContext

    }

}