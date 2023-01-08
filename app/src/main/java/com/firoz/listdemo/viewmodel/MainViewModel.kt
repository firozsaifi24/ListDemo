package com.firoz.listdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.firoz.listdemo.api.ApiClient
import com.firoz.listdemo.datastore.UsersDataSource
import com.firoz.listdemo.model.UserDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun getUsersFlow(): LiveData<PagingData<UserDataResponse>> {
        val config = PagingConfig(pageSize = 10, enablePlaceholders = true)
        val datasource = UsersDataSource(ApiClient.request!!)
        return Pager(config = config, pagingSourceFactory = { datasource }).liveData
    }

}