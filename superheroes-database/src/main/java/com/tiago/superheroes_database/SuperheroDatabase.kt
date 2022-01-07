package com.tiago.superheroes_database

import com.tiago.superheroes_domain.Superhero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SuperheroDatabase(
    private val superheroDao: SuperheroDao,
    private val mapper: SuperheroDatabaseMapper
) {

    fun getAll(): Flow<List<Superhero>> {
        return superheroDao.getAll()
            .map { dbItems -> dbItems.map { mapper.mapToEntity(it) } }
    }

    fun getInSquad(): Flow<List<Superhero>> {
        return superheroDao.getInSquad()
            .map { dbItems -> dbItems.map { mapper.mapToEntity(it) } }
    }

    fun insertAll(apiSuperheroes: List<Superhero>) {
        val dbItems = apiSuperheroes.map { mapper.mapToDbItem(it) }
        //Keep isInSquad value
        superheroDao.upsert(dbItems)
    }

    fun update(superhero: Superhero) {
        val dbItem = mapper.mapToDbItem(superhero)
        superheroDao.update(dbItem)
    }
}