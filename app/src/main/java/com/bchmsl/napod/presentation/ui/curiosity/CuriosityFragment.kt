package com.bchmsl.napod.presentation.ui.curiosity

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.bchmsl.napod.common.extensions.collectViewState
import com.bchmsl.napod.databinding.FragmentCuriosityBinding
import com.bchmsl.napod.presentation.ui.base.BaseFragment
import com.bchmsl.napod.presentation.ui.home.home.asynchronously
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuriosityFragment :
    BaseFragment<FragmentCuriosityBinding>(FragmentCuriosityBinding::inflate) {
    private val viewModel: CuriosityViewModel by viewModels()
    private val curiosityAdapter by lazy { CuriosityAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getApod(true, null, 1500)
    }
    override fun start() {
        observe()
    }

    private fun observe() {
        binding.rvCuriosity.adapter = curiosityAdapter
        asynchronously {
            viewModel.remoteApodState.collectViewState(binding) {
                curiosityAdapter.submitData(it)
                binding.btnChangeDate.text = curiosityAdapter.snapshot()[0]?.earthDate
            }
        }
    }

}