package com.bchmsl.napod.presentation.mapper.apod

import com.bchmsl.napod.domain.model.ApodDomainModel
import com.bchmsl.napod.presentation.ui.home.model.ApodUiModel

fun List<ApodDomainModel>.domainToApodUiModelList(): List<ApodUiModel> =
    map { it.toApodUiModel() }

fun ApodDomainModel.toApodUiModel() =
    ApodUiModel(
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        mediaType = mediaType,
        author = author,
        serviceVersion = serviceVersion,
        title = title,
        url = url
    )

fun ApodUiModel.toApodDomainModel() =
    ApodDomainModel(
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        mediaType = mediaType,
        author = author,
        serviceVersion = serviceVersion,
        title = title,
        url = url
    )