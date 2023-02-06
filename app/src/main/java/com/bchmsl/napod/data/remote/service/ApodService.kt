package com.bchmsl.napod.data.remote.service

import com.bchmsl.napod.data.remote.model.ApodResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {
    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int
    ): Response<List<ApodResponseDto>>
}