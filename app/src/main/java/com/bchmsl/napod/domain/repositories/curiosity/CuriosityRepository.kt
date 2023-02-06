package com.bchmsl.napod.domain.repositories.curiosity

import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.domain.model.CuriosityDomainModel
import kotlinx.coroutines.flow.Flow

interface CuriosityRepository {
    suspend fun getCuriosityPhotos(fetchFromRemote: Boolean, page: Int?, date: String?, sol: Int?): Flow<Resource<List<CuriosityDomainModel>>>
    suspend fun saveCuriosityPhoto(curiosityDomainModel: CuriosityDomainModel)
    suspend fun removeCuriosityPhoto(curiosityDomainModel: CuriosityDomainModel)
}