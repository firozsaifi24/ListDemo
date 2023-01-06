package com.firoz.listdemo.api

import android.util.Log
import com.firoz.listdemo.R
import com.firoz.listdemo.MainApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiResponseCallback<T> : Callback<T?> {
    private val TAG = ApiResponseCallback::class.java.simpleName
    override fun onResponse(
        call: Call<T?>,
        response: Response<T?>
    ) {
        Log.i(TAG, "RES : " + "Success")
        if (response.body() != null && response.raw().code == 200) {
            onSuccess(response.body())
        } else if (response.raw().code == 401) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.session_expired)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }

        } else if (response.raw().code == 500) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.error_500)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }
        }
        else if (response.raw().code == 400) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.error_400)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }
        }
        else if (response.raw().code == 403) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.error_403)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }
        }
        else if (response.raw().code == 404) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.error_404)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }
        }
        else if (response.raw().code == 502) {
            try {
                val e1 = GenricError()
                e1.message= MainApplication.appContext.getString(R.string.error_502)
                //e1.error= null
                e1.success= false
                onError(e1)
            } catch (e: Exception) {
                Log.d(TAG, "api exception: " + e.message)
                onError(defaultErrorMsg)
            }
        }
        else {
            onError(defaultErrorMsg)
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        Log.i(TAG, "ERROR FAIL : " + t.message)
        if (t is NetworkConnectionInterceptor.NoConnectivityException) {
            // show No Connectivity message to user or do whatever you want.
            onError(getNoConnectivityErrorMsg(t.message))
        } else {
            onError(defaultErrorMsg)
        }
    }

    abstract fun onSuccess(response: T?)
    abstract fun onError(msg: GenricError?)
    private val defaultErrorMsg: GenricError
        private get() {
            val error = GenricError()

            error.message = MainApplication.appContext.getString(R.string.something_went_wrong)

            onError(error)
            return error
        }

    private fun getNoConnectivityErrorMsg(msg: String?): GenricError {
        val error = GenricError()

        error.message= msg

        onError(error)
        return error
    }
}