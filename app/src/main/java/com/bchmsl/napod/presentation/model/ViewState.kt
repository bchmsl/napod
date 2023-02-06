package com.bchmsl.napod.presentation.model

import kotlinx.coroutines.flow.MutableStateFlow

data class ViewState<T>(
    val data: T? = null,
    val error: Throwable? = null,
    val isLoading: Boolean? = null,
) {
    companion object {
        fun <T> MutableStateFlow<ViewState<T>>.success(data: T) { this.value = this.value.copy(data = data, isLoading = false) }
        fun <T> MutableStateFlow<ViewState<T>>.error(error: Throwable) { this.value = this.value.copy(error = error, isLoading = false) }
        fun <T> MutableStateFlow<ViewState<T>>.loading(isLoading: Boolean) { this.value = this.value.copy(isLoading = isLoading)}
        fun <T> MutableStateFlow<ViewState<T>>.reset() { this.value = this.value.copy(null,null, null) }
    }
}
