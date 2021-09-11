package com.adhika.testhilt.data.repository

import com.adhika.testhilt.data.network.BaseApi
import com.adhika.testhilt.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}