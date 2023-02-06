package com.bchmsl.napod.data.remote.model

import com.squareup.moshi.Json

data class ApodResponseDto(
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val copyright: String?,
    @field:Json(name="media_type")
    val mediaType: String?,
    @field:Json(name="service_version")
    val serviceVersion: String?,
    val title: String?,
    val url: String?
)
