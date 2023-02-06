package com.bchmsl.napod.data.remote.service

import com.bchmsl.napod.data.remote.model.CuriosityResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CuriosityService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getCuriosity(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("earth_date") earthDate: String?,
        @Query("sol") sol: Int?,
    ): Response<CuriosityResponseDto>
}