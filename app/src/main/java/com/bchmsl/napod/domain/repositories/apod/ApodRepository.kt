package com.bchmsl.napod.domain.repositories.apod

import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.domain.model.ApodDomainModel
import kotlinx.coroutines.flow.Flow

interface ApodRepository {
    suspend fun getApod(count: Int): Flow<Resource<List<ApodDomainModel>>>
    suspend fun saveApod(apodDomainModel: ApodDomainModel)
    suspend fun removeApod(apodDomainModel: ApodDomainModel)
}