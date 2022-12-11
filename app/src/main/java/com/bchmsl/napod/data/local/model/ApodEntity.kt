package com.bchmsl.napod.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class ApodEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val date: String?,
    val author: String?,
    val explanation: String?,
    val hdurl: String?,
    val mediaType: String?,
    val serviceVersion: String?,
    val title: String?,
    val url: String?
)
