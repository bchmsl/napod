package com.bchmsl.napod.data.mapper.apod

import com.bchmsl.napod.data.remote.model.ApodResponseDto
import com.bchmsl.napod.domain.model.ApodDomainModel

fun List<ApodResponseDto>.dtoToApodDomainModelList(): List<ApodDomainModel> =
    map { it.toApodDomainModel() }


fun ApodResponseDto.toApodDomainModel() =
    ApodDomainModel(
        date = date?: "",
        explanation = explanation?: "",
        hdurl = hdurl?: "",
        author = copyright?: "",
        mediaType = mediaType?: "",
        serviceVersion = serviceVersion?: "",
        title = title?: "",
        url = url?: ""
    )