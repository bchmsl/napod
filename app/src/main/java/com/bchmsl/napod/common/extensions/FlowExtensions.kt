package com.bchmsl.napod.common.extensions

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.bchmsl.napod.presentation.model.ViewState
import kotlinx.coroutines.flow.Flow

suspend fun <T: ViewState<D>, D> Flow<T>.collectViewState(binding: ViewBinding,progressBar: ProgressBar? = null, block: suspend (data: D) -> Unit) {
    collect{ viewState ->
        viewState.isLoading?.let { isLoading ->
            progressBar?.isVisible = isLoading
        }
        viewState.data?.let {data ->
            block(data)
        }
        viewState.error?.let {error ->
            error.localizedMessage?.let { it1 -> binding.root.makeSnackbar(it1, true) }
        }
    }
}