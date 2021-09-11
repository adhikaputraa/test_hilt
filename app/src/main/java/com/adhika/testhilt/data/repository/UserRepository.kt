package com.adhika.testhilt.data.repository

import com.adhika.testhilt.data.network.UserApi
import com.adhika.testhilt.data.repository.BaseRepository

class UserRepository(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }

}