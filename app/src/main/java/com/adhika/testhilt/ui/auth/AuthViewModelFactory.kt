package com.adhika.testhilt.ui.auth

import com.adhika.testhilt.data.repository.AuthRepository

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : Factory<AuthViewModel> {
    override fun create(): AuthViewModel {
        return AuthViewModel(authRepository)
    }
}