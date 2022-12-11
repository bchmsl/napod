package com.bchmsl.napod.presentation.ui.home.home

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bchmsl.napod.common.extensions.collectViewState
import com.bchmsl.napod.databinding.FragmentHomeBinding
import com.bchmsl.napod.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private val apodAdapter by lazy { ApodAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getApod(30)
    }
    override fun start() {
        observers()
    }

    private fun observers() {
        binding.rvApod.adapter = apodAdapter
        asynchronously {
            viewModel.apodState.collectViewState(binding, binding.progressBar) {
                apodAdapter.submitList(it)
                listeners()
            }
        }
    }

    private fun listeners(){
        apodAdapter.onApodClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToApodOpenedFragment(it))
        }
    }

}