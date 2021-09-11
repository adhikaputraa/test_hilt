package com.adhika.testhilt.data.repository

import com.adhika.testhilt.data.UserPreferences
import com.adhika.testhilt.data.network.AuthApi
import javax.inject.Inject


class AuthRepository @Inject constructor (
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }

}