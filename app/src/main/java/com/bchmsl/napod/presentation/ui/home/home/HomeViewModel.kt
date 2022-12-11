package com.bchmsl.napod.presentation.ui.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.napod.common.extensions.doOnFailure
import com.bchmsl.napod.common.extensions.doOnSuccess
import com.bchmsl.napod.domain.repositories.apod.ApodRepository
import com.bchmsl.napod.presentation.mapper.apod.domainToApodUiModelList
import com.bchmsl.napod.presentation.model.ViewState
import com.bchmsl.napod.presentation.model.ViewState.Companion.error
import com.bchmsl.napod.presentation.model.ViewState.Companion.loading
import com.bchmsl.napod.presentation.model.ViewState.Companion.success
import com.bchmsl.napod.presentation.ui.home.model.ApodUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApodRepository
) : ViewModel() {

    private val _apodState = MutableStateFlow<ViewState<List<ApodUiModel>>>(ViewState())
    val apodState = _apodState.asStateFlow()

    fun getApod(count: Int) {
        viewModelScope.launch {
            repository.getApod(count).collect { resource ->
                _apodState.loading(resource.isLoading)
                resource.doOnSuccess {
                    _apodState.success(it.domainToApodUiModelList())
                }.doOnFailure {
                    _apodState.error(it)
                }
            }
        }
    }
}