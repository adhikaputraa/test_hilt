package com.adhika.testhilt.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.adhika.testhilt.R
import com.adhika.testhilt.data.network.RemoteDataSource
import com.adhika.testhilt.data.network.Resource
import com.adhika.testhilt.data.network.UserApi
import com.adhika.testhilt.data.repository.UserRepository
import com.adhika.testhilt.data.responses.User
import com.adhika.testhilt.databinding.FragmentHomeBinding
import com.adhika.testhilt.ui.handleApiError
import com.adhika.testhilt.ui.logout
import com.adhika.testhilt.ui.visible

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(UserApi::class.java, requireContext())
        val userRepository = UserRepository(api)
        viewModel = HomeViewModel(userRepository)

        binding.progressbar.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }
}