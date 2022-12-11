package com.bchmsl.napod.data.mapper.apod

import com.bchmsl.napod.data.local.model.ApodEntity
import com.bchmsl.napod.domain.model.ApodDomainModel

fun List<ApodEntity>.entityToApodDomainModelList(): List<ApodDomainModel> =
    map { it.toApodDomainModel() }


fun ApodEntity.toApodDomainModel() =
    ApodDomainModel(
        date = date?: "",
        explanation = explanation?: "",
        hdurl = hdurl?: "",
        mediaType = mediaType?: "",
        author = author?: "",
        serviceVersion = serviceVersion?: "",
        title = title?: "",
        url = url?: ""
    )

fun ApodDomainModel.toApodEntity() =
    ApodEntity(
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        mediaType = mediaType,
        author = author,
        serviceVersion = serviceVersion,
        title = title,
        url = url
    )