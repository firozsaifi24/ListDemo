package com.firoz.listdemo.datastore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.firoz.listdemo.model.UserDataResponse
import com.firoz.listdemo.api.ApiRequests

class UsersDataSource(
    private val api: ApiRequests,
) : PagingSource<Int, UserDataResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDataResponse> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = api.getUserDataList(
                currentLoadingPageKey,
                10
            )
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = if (response.isEmpty()) null else currentLoadingPageKey + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserDataResponse>): Int? {
        return null
    }

}