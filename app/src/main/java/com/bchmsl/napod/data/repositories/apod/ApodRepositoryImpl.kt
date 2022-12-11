package com.bchmsl.napod.data.repositories.apod

import android.util.Log
import com.bchmsl.napod.common.handlers.Resource
import com.bchmsl.napod.data.local.database.NapodDatabase
import com.bchmsl.napod.data.mapper.apod.dtoToApodDomainModelList
import com.bchmsl.napod.data.mapper.apod.entityToApodDomainModelList
import com.bchmsl.napod.data.mapper.apod.toApodEntity
import com.bchmsl.napod.data.remote.helpers.ApiHelper
import com.bchmsl.napod.data.remote.service.ApodService
import com.bchmsl.napod.domain.model.ApodDomainModel
import com.bchmsl.napod.domain.repositories.apod.ApodRepository
import com.bchmsl.napod.domain.repositories.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
    private val api: ApodService,
    private val db: NapodDatabase,
    private val baseRepository: BaseRepository
): ApodRepository {

    override suspend fun getApod(count: Int): Flow<Resource<List<ApodDomainModel>>> = flow{
        emit(Resource.Loading())
        if (count != -1){
            emit(fetchFromRemote(count))
        }else{
            emit(Resource.Success(getFromDatabase()))
        }
    }

    private suspend fun fetchFromRemote(count: Int): Resource<List<ApodDomainModel>>{
        return baseRepository.safeApiCall({
            api.getApod(ApiHelper.API_KEY, count)
        },{
            this.dtoToApodDomainModelList()
        })
    }

    private suspend fun getFromDatabase(): List<ApodDomainModel>{
        return db.apodDao.getApod().entityToApodDomainModelList()
    }

    override suspend fun saveApod(apodDomainModel: ApodDomainModel) {
        db.apodDao.insertApod(apodDomainModel.toApodEntity())
    }

    override suspend fun removeApod(apodDomainModel: ApodDomainModel) {
        db.apodDao.removeApod(apodDomainModel.toApodEntity())
    }

}