package com.firoz.listdemo.api

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
object ApiClient {

    private lateinit var apiRequest: ApiRequests
    private lateinit var mContext: Context
    fun init(
        requestClass: Class<ApiRequests>,
        context: Context
    ) {
        mContext = context
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        // setup timeout vale offset
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(ApiInterceptor()).addInterceptor(interceptor)
            .addInterceptor(NetworkConnectionInterceptor(mContext))
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        //Log.d("url", BASE_URL)
        apiRequest = retrofit.create(requestClass)
    }

    /*if(!AppUtils.isNetworkAvailable(mContext))
            Toast.makeText(mContext,"No Internet" , Toast.LENGTH_LONG).show();*/
    val request: ApiRequests?
        get() =/*if(!AppUtils.isNetworkAvailable(mContext))
                Toast.makeText(mContext,"No Internet" , Toast.LENGTH_LONG).show();*/
            apiRequest
}
