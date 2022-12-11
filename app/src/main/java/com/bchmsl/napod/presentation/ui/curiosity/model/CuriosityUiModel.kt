package com.bchmsl.napod.presentation.ui.curiosity.model

data class CuriosityUiModel(
    val id: Int,
    val sol: Int,
    val camera: Camera,
    val imgSrc: String,
    val earthDate: String,
    val rover: Rover
){
    data class Camera(
        val id: Int,
        val name: String,
        val roverId: Int,
        val fullName: String
    )
    data class Rover(
        val id: Int,
        val name: String,
        val landingDate: String,
        val launchDate: String,
        val status: String
    )
}
