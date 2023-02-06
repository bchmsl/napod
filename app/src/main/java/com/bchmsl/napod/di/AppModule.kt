package com.bchmsl.napod.di

import android.content.Context
import androidx.room.Room
import com.bchmsl.napod.data.local.database.NapodDatabase
import com.bchmsl.napod.data.remote.helpers.ApiHelper
import com.bchmsl.napod.data.remote.service.ApodService
import com.bchmsl.napod.data.remote.service.CuriosityService
import com.bchmsl.napod.data.repositories.apod.ApodRepositoryImpl
import com.bchmsl.napod.domain.repositories.apod.ApodRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NapodDatabase::class.java,
        "napod_database"
    ).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiHelper.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApodService(retrofit: Retrofit): ApodService = retrofit.create(ApodService::class.java)

    @Provides
    @Singleton
    fun provideCuriosityService(retrofit: Retrofit): CuriosityService = retrofit.create(CuriosityService::class.java)

}