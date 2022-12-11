package com.bchmsl.napod.domain.repositories.base

import com.bchmsl.napod.common.handlers.Resource
import retrofit2.Response

interface BaseRepository {
    suspend fun <T, M> safeApiCall(
        request: suspend () -> Response<T>,
        map: T.() -> M
    ): Resource<M>
}