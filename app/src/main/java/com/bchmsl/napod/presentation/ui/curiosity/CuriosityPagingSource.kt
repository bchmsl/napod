package com.bchmsl.napod.presentation.ui.curiosity

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bchmsl.napod.common.extensions.doOnFailure
import com.bchmsl.napod.common.extensions.doOnSuccess
import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.data.remote.helpers.ApiHelper
import com.bchmsl.napod.data.remote.service.CuriosityService
import com.bchmsl.napod.domain.repositories.curiosity.CuriosityRepository
import com.bchmsl.napod.presentation.mapper.curiosity.domainToCuriosityUiModelList
import com.bchmsl.napod.presentation.ui.curiosity.model.CuriosityUiModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class CuriosityPagingSource(
    private val repository: CuriosityRepository,
    private val date: String?,
    private val sol: Int?
) : PagingSource<Int, CuriosityUiModel>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CuriosityUiModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        lateinit var loadResult: LoadResult<Int, CuriosityUiModel>
        repository.getCuriosityPhotos(true, page, date, sol).collect { resource ->
            when (resource) {
                is Resource.Success -> loadResult = LoadResult.Page(
                    data = resource.data.domainToCuriosityUiModelList().toList(),
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (resource.data.isEmpty()) null else page + 1
                )
                is Resource.Error -> loadResult = LoadResult.Error(
                    resource.error
                )
                else -> {}
            }
        }
        return loadResult
    }

    override fun getRefreshKey(state: PagingState<Int, CuriosityUiModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}