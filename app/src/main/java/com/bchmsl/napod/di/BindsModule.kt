package com.bchmsl.napod.di

import com.bchmsl.napod.data.repositories.apod.ApodRepositoryImpl
import com.bchmsl.napod.data.repositories.base.BaseRepositoryImpl
import com.bchmsl.napod.data.repositories.curiosity.CuriosityRepositoryImpl
import com.bchmsl.napod.domain.repositories.apod.ApodRepository
import com.bchmsl.napod.domain.repositories.base.BaseRepository
import com.bchmsl.napod.domain.repositories.curiosity.CuriosityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    abstract fun bindApodRepository(repository: ApodRepositoryImpl): ApodRepository

    @Binds
    @Singleton
    abstract fun bindBaseRepository(repository: BaseRepositoryImpl): BaseRepository

    @Binds
    @Singleton
    abstract fun bindCuriosityRepository(repository: CuriosityRepositoryImpl): CuriosityRepository
}