package com.adhika.testhilt.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adhika.testhilt.R
import kotlinx.coroutines.launch
import com.adhika.testhilt.data.UserPreferences
import com.adhika.testhilt.data.network.RemoteDataSource
import com.adhika.testhilt.data.network.UserApi
import com.adhika.testhilt.data.repository.UserRepository
import com.adhika.testhilt.ui.auth.AuthActivity
import com.adhika.testhilt.ui.startNewActivity

class HomeActivity : AppCompatActivity() {

    lateinit var userPreferences: UserPreferences
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userPreferences = UserPreferences(this)
        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(UserApi::class.java,this)
        val authRepository = UserRepository(api)
        viewModel = HomeViewModel(authRepository)
    }

    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        userPreferences.clear()
        startNewActivity(AuthActivity::class.java)
    }
}