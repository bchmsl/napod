package com.bchmsl.napod.domain.model

data class ApodDomainModel(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val author: String,
    val serviceVersion: String,
    val title: String,
    val url: String
)
