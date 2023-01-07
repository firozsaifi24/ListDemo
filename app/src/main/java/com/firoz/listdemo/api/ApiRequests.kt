package com.firoz.listdemo.api

import com.firoz.listdemo.model.UserDataResponse
import retrofit2.http.*

interface ApiRequests {
    interface FIELD {
        companion object {
            const val PAGE = "page"
            const val LIMIT = "limit"
        }
    }

    @GET("v2/list")
    suspend fun getUserDataList(
        @Query(FIELD.PAGE) page: Int,
        @Query(FIELD.LIMIT) limit: Int,
        ): List<UserDataResponse>

}
