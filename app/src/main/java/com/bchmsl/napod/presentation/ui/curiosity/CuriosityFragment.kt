package com.bchmsl.napod.presentation.ui.curiosity

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bchmsl.napod.common.extensions.addDays
import com.bchmsl.napod.common.extensions.beautifyDate
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
    private val datePickerFragment by lazy { DatePickerFragment() }

    companion object {
        var date = "0-0-0"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showDialog()
    }


    override fun start() {
        observe()
        listeners()
    }

    private fun listeners() {
        binding.btnChangeDate.setOnClickListener {
            showDialog()
        }

        datePickerFragment.dateSetCallback = { day, month, year ->
            date = "$year-$month-$day"
            setDate(date)
        }

        binding.btnNextDate.setOnClickListener {
            setDate(null, 1)
        }

        binding.btnPreviousDate.setOnClickListener {
            setDate(null, -1)

        }
    }

    private fun setDate(date: String? = null, daysToAdd: Int? = null) {
        daysToAdd?.let{
            Companion.date = Companion.date.addDays(daysToAdd)
        }
        date?.let{
            Companion.date = date
        }
        binding.btnChangeDate.text = Companion.date.beautifyDate()
        viewModel.getApod(true, Companion.date, null)
    }

    private fun observe() {
        binding.rvCuriosity.adapter = curiosityAdapter
        asynchronously {
            viewModel.remoteApodState.collectViewState(binding) {
                curiosityAdapter.submitData(lifecycle, it)
            }
        }
        asynchronously {
            viewModel.loaderState.collect{
                binding.progressBar.isVisible = it
            }
        }
    }

    private fun showDialog() {
        datePickerFragment.show(parentFragmentManager, "datePicker")
    }

}