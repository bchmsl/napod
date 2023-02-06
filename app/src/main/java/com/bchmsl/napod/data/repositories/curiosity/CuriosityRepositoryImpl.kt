package com.bchmsl.napod.data.repositories.curiosity

import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.data.local.database.NapodDatabase
import com.bchmsl.napod.data.mapper.curiosity.toCuriosityDomainModelList
import com.bchmsl.napod.data.mapper.curiosity.entityToCuriosityDomainModelList
import com.bchmsl.napod.data.mapper.curiosity.toCuriosityEntity
import com.bchmsl.napod.data.remote.helpers.ApiHelper
import com.bchmsl.napod.data.remote.service.CuriosityService
import com.bchmsl.napod.domain.model.CuriosityDomainModel
import com.bchmsl.napod.domain.repositories.base.BaseRepository
import com.bchmsl.napod.domain.repositories.curiosity.CuriosityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CuriosityRepositoryImpl @Inject constructor(
    private val api: CuriosityService,
    private val db: NapodDatabase,
    private val baseRepository: BaseRepository
) : CuriosityRepository {
    override suspend fun getCuriosityPhotos(
        fetchFromRemote: Boolean,
        page: Int?,
        date: String?,
        sol: Int?
    ): Flow<Resource<List<CuriosityDomainModel>>> = flow {
        if (fetchFromRemote) {
            emit(fetchFromRemote(page, date, sol))
        } else {
            emit(Resource.Success(getFromDatabase()))
        }
    }

    private suspend fun fetchFromRemote(page: Int?, date: String?, sol: Int?): Resource<List<CuriosityDomainModel>> {
        return baseRepository.safeApiCall({
            api.getCuriosity(ApiHelper.API_KEY, page ?: 0, date, sol)
        }, {
            this.toCuriosityDomainModelList()
        })
    }

    private suspend fun getFromDatabase(): List<CuriosityDomainModel> {
        return db.curiosityDao.getCuriosityPhotos().entityToCuriosityDomainModelList()
    }

    override suspend fun saveCuriosityPhoto(curiosityDomainModel: CuriosityDomainModel) {
        db.curiosityDao.insertCuriosityPhoto(curiosityDomainModel.toCuriosityEntity())
    }

    override suspend fun removeCuriosityPhoto(curiosityDomainModel: CuriosityDomainModel) {
        db.curiosityDao.removeCuriosityPhoto(curiosityDomainModel.toCuriosityEntity())
    }

}