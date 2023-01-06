package com.firoz.listdemo

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplicationContext = this
    }

    companion object{

        private lateinit var mApplicationContext: MainApplication
        val appContext: Context
            get() = mApplicationContext

    }

}