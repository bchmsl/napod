package com.bchmsl.napod.data.repositories.base

import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.domain.repositories.base.BaseRepository
import retrofit2.Response
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(): BaseRepository {

    override suspend fun <T, M> safeApiCall(
        request: suspend () -> Response<T>,
        map: T.() -> M
    ): Resource<M> {
        return try {
            val response = request.invoke()
                val body = response.body()
                if (response.isSuccessful) {
                    Resource.Success(body!!.map())
                } else {
                    Resource.Error(Throwable(response.errorBody().toString()))
                }
        } catch (e: Throwable) {
            Resource.Error(e)
        }
    }

}