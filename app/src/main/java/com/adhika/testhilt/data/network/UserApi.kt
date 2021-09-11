package com.adhika.testhilt.data.network

import com.adhika.testhilt.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi : BaseApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}