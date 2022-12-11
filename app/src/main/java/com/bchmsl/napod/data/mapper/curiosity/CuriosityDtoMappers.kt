package com.bchmsl.napod.data.mapper.curiosity

import com.bchmsl.napod.data.remote.model.CuriosityResponseDto
import com.bchmsl.napod.domain.model.CuriosityDomainModel

fun CuriosityResponseDto.toCuriosityDomainModelList() =
    photos?.map { it?.toCuriosityDomainModel() ?: CuriosityDomainModel(
        id = 0,
        sol = 0,
        camera = CuriosityDomainModel.Camera(
            id = 0,
            name = "",
            roverId = 0,
            fullName = ""
        ),
        imgSrc = "",
        earthDate = "",
        rover = CuriosityDomainModel.Rover(
            id = 0,
            name = "",
            landingDate = "",
            launchDate = "",
            status = ""
        )
    ) } ?: emptyList()



fun CuriosityResponseDto.Photo.toCuriosityDomainModel() =
    CuriosityDomainModel(
        id = id ?: 0,
        sol = sol ?: 0,
        camera = camera?.toCuriosityDomainModel() ?: CuriosityDomainModel.Camera(0, "", 0, ""),
        imgSrc = imgSrc ?: "",
        earthDate = earthDate ?: "",
        rover = rover?.toCuriosityDomainModel() ?: CuriosityDomainModel.Rover(0, "", "", "", "")
    )


fun CuriosityResponseDto.Photo.Camera.toCuriosityDomainModel() =
    CuriosityDomainModel.Camera(
        id = id ?: 0,
        name = name ?: "",
        roverId = roverId ?: 0,
        fullName = fullName ?: ""
    )

fun CuriosityResponseDto.Photo.Rover.toCuriosityDomainModel() =
    CuriosityDomainModel.Rover(
        id = id ?: 0,
        name = name ?: "",
        landingDate = landingDate ?: "",
        launchDate = launchDate ?: "",
        status = status ?: ""
    )