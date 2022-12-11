package com.bchmsl.napod.presentation.mapper.curiosity

import com.bchmsl.napod.domain.model.CuriosityDomainModel
import com.bchmsl.napod.presentation.ui.curiosity.model.CuriosityUiModel

fun List<CuriosityDomainModel>.domainToCuriosityUiModelList(): List<CuriosityUiModel> =
    map { it.toCuriosityUiModel() }

fun CuriosityDomainModel.toCuriosityUiModel() =
    CuriosityUiModel(
        id = id,
        sol = sol,
        camera = camera.toCuriosityUiModel(),
        imgSrc = imgSrc,
        earthDate = earthDate,
        rover = rover.toCuriosityUiModel()
    )

fun CuriosityDomainModel.Camera.toCuriosityUiModel() =
    CuriosityUiModel.Camera(
        id = id,
        name = name,
        roverId = roverId,
        fullName = fullName
    )

fun CuriosityDomainModel.Rover.toCuriosityUiModel() =
    CuriosityUiModel.Rover(
        id = id,
        name = name,
        landingDate = landingDate,
        launchDate = launchDate,
        status = status
    )

fun CuriosityUiModel.toCuriosityDomainModel() =
    CuriosityDomainModel(
        id = id,
        sol = sol,
        camera = camera.toCuriosityDomainModel(),
        imgSrc = imgSrc,
        earthDate = earthDate,
        rover = rover.toCuriosityDomainModel()
    )

fun CuriosityUiModel.Camera.toCuriosityDomainModel() =
    CuriosityDomainModel.Camera(
        id = id,
        name = name,
        roverId = roverId,
        fullName = fullName
    )

fun CuriosityUiModel.Rover.toCuriosityDomainModel() =
    CuriosityDomainModel.Rover(
        id = id,
        name = name,
        landingDate = landingDate,
        launchDate = launchDate,
        status = status
    )