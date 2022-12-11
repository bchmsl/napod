package com.bchmsl.napod.presentation.ui.curiosity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.bchmsl.napod.common.extensions.doOnFailure
import com.bchmsl.napod.common.extensions.doOnSuccess
import com.bchmsl.napod.domain.repositories.curiosity.CuriosityRepository
import com.bchmsl.napod.presentation.mapper.curiosity.domainToCuriosityUiModelList
import com.bchmsl.napod.presentation.model.ViewState
import com.bchmsl.napod.presentation.model.ViewState.Companion.error
import com.bchmsl.napod.presentation.model.ViewState.Companion.success
import com.bchmsl.napod.presentation.ui.curiosity.model.CuriosityUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CuriosityViewModel @Inject constructor(
    private val repository: CuriosityRepository
) : ViewModel() {

    private val _remoteApodState = MutableStateFlow<ViewState<PagingData<CuriosityUiModel>>>(ViewState())
    val remoteApodState = _remoteApodState.asStateFlow()

    private val _localApodState = MutableStateFlow<ViewState<List<CuriosityUiModel>>>(ViewState())
    val localApodState = _localApodState.asStateFlow()

    fun getApod(fetchFromRemote: Boolean, date: String?, sol: Int?) {
        viewModelScope.launch {
            if (fetchFromRemote) {
                Pager(
                    config = PagingConfig(30),
                    pagingSourceFactory = { CuriosityPagingSource(repository, date, sol) }
                ).flow.collect {
                    _remoteApodState.success(it)
                }
            }else{
                repository.getCuriosityPhotos(false, null, null, null).collect{ resource ->
                    resource.doOnSuccess {
                        _localApodState.success(it.domainToCuriosityUiModelList())
                    }.doOnFailure {
                        _localApodState.error(it)
                    }
                }
            }
        }
    }
}
