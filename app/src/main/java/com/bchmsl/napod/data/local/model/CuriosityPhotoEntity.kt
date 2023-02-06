package com.bchmsl.napod.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CuriosityPhotoEntity(
    @PrimaryKey val id: Int?,
    val sol: Int?,
    @Embedded val camera: Camera?,
    val imgSrc: String?,
    val earthDate: String?,
    @Embedded val rover: Rover?
) {
    data class Camera(
        val cameraId: Int?,
        val cameraName: String?,
        val cameraRoverId: Int?,
        val fullName: String?
    )

    data class Rover(
        val roverId: Int?,
        val roverName: String?,
        val landingDate: String?,
        val launchDate: String?,
        val status: String?
    )
}
