package com.tiago.superheroes_domain.repository

import com.tiago.superheroes_domain.Superhero
import kotlinx.coroutines.flow.Flow

interface SuperheroRepository {
    suspend fun getAll(): Flow<List<Superhero>>
    suspend fun getInSquad(): Flow<List<Superhero>>
    suspend fun update(superhero: Superhero)
}