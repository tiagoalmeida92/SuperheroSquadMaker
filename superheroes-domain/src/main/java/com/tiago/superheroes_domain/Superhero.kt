package com.tiago.superheroes_domain

import java.io.Serializable

data class Superhero(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isInSquad: Boolean = false
): Serializable
