package com.bchmsl.napod.data.mapper.curiosity

import com.bchmsl.napod.data.local.model.CuriosityPhotoEntity
import com.bchmsl.napod.domain.model.CuriosityDomainModel

fun List<CuriosityPhotoEntity>.entityToCuriosityDomainModelList(): List<CuriosityDomainModel> =
    map { it.toCuriosityDomainModel() }

fun CuriosityPhotoEntity.toCuriosityDomainModel() =
    CuriosityDomainModel(
        id = id?: 0,
        sol = sol ?: 0,
        camera = camera?.toCuriosityDomainModel() ?: CuriosityDomainModel.Camera(0, "", 0, ""),
        imgSrc = imgSrc ?: "",
        earthDate = earthDate ?: "",
        rover = rover?.toCuriosityDomainModel() ?: CuriosityDomainModel.Rover(0, "", "", "", "")
    )

fun CuriosityPhotoEntity.Camera.toCuriosityDomainModel() =
    CuriosityDomainModel.Camera(
        id = cameraId ?: 0,
        name = cameraName ?: "",
        roverId = cameraRoverId ?: 0,
        fullName = fullName ?: ""
    )

fun CuriosityPhotoEntity.Rover.toCuriosityDomainModel() =
    CuriosityDomainModel.Rover(
        id = roverId ?: 0,
        name = roverName ?: "",
        landingDate = landingDate ?: "",
        launchDate = launchDate ?: "",
        status = status ?: ""
    )


fun CuriosityDomainModel.toCuriosityEntity() =
    CuriosityPhotoEntity(
        id = id,
        sol = sol,
        camera = camera.toCuriosityEntity(),
        imgSrc = imgSrc,
        earthDate = earthDate,
        rover = rover.toCuriosityEntity()
    )

fun CuriosityDomainModel.Camera.toCuriosityEntity() =
    CuriosityPhotoEntity.Camera(
        cameraId = id,
        cameraName = name,
        cameraRoverId = roverId,
        fullName = fullName
    )

fun CuriosityDomainModel.Rover.toCuriosityEntity() =
    CuriosityPhotoEntity.Rover(
        roverId = id,
        roverName = name,
        landingDate = landingDate,
        launchDate = launchDate,
        status = status
    )
