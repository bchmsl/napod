package com.bchmsl.napod.data.remote.model

import com.squareup.moshi.Json

data class CuriosityResponseDto(
    val photos: List<Photo?>?
){
    data class Photo(
        val id: Int?,
        val sol: Int?,
        val camera: Camera?,
        @field:Json(name = "img_src")
        val imgSrc: String?,
        @field:Json(name = "earth_date")
        val earthDate: String?,
        val rover: Rover?
    ){
        data class Camera(
            val id: Int?,
            val name: String?,
            @field:Json(name="rover_id")
            val roverId: Int?,
            @field:Json(name="full_name")
            val fullName: String?
        )

        data class Rover(
            val id: Int?,
            val name: String?,
            @field:Json(name="landing_date")
            val landingDate: String?,
            @field:Json(name="launch_date")
            val launchDate: String?,
            val status: String?
        )
    }
}
