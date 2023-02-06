package com.bchmsl.napod.presentation.ui.home.apod_opened

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bchmsl.napod.common.extensions.loadImage
import com.bchmsl.napod.databinding.FragmentApodOpenedBinding
import com.bchmsl.napod.presentation.ui.base.BaseFragment


class ApodOpenedFragment : BaseFragment<FragmentApodOpenedBinding>(FragmentApodOpenedBinding::inflate) {
    private val args: ApodOpenedFragmentArgs by navArgs()
    override fun start() {
        setLayout()
        listeners()
    }

    private fun listeners() {
        binding.cbQuality.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.progressBar.isVisible = true
            if (isChecked) {
                binding.ivImage.loadImage(args.apod.hdurl)
            } else {
                binding.ivImage.loadImage(args.apod.url)
            }
            binding.progressBar.isVisible = false
        }
        binding.ibtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setLayout() {
        binding.apply {
            with(args.apod) {
                ivImage.loadImage(hdurl)
                tvTitle.text = title
                tvDate.text = date
                tvDescription.text = explanation
                tvAuthor.text = author
            }
        }
    }
}