package com.bchmsl.napod.presentation.ui.home.model

import java.io.Serializable

data class ApodUiModel(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val mediaType: String,
    val serviceVersion: String,
    val author: String,
    val title: String,
    val url: String
): Serializable
