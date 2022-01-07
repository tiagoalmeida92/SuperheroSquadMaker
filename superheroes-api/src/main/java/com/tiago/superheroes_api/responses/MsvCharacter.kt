package com.tiago.superheroes_api.responses

data class MsvCharacter(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: MsvThumbnail
)
