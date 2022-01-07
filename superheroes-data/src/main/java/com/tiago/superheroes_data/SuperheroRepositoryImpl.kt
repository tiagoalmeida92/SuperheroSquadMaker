package com.tiago.superheroes_data

import com.tiago.superheroes_api.SuperheroService
import com.tiago.superheroes_database.SuperheroDatabase
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_domain.repository.SuperheroRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception

class SuperheroRepositoryImpl(
    private val database: SuperheroDatabase,
    private val service: SuperheroService
) : SuperheroRepository {

    companion object {
        private const val REFRESH_DELAY = 60 * 60 * 1000L
        private const val RETRY_DELAY = 5000L
    }

    override suspend fun getAll(): Flow<List<Superhero>> {
        return combine(
            database.getAll(),
            //Refresh data from API
            flow {
                do {
                    var updated = false
                    try {
                        val apiSuperheroes = service.getSuperheroes()
                        database.insertAll(apiSuperheroes)//check thread
                        updated = true
                    } catch (e: Exception) {

                    }
                    emit(Unit)
                    if (updated) {
                        delay(REFRESH_DELAY)
                    } else {
                        delay(RETRY_DELAY)
                    }
                } while (true)
            }
        ) { superheroes: List<Superhero>, _: Unit ->
            superheroes
        }
    }

    override suspend fun getInSquad(): Flow<List<Superhero>> {
        return database.getInSquad()
    }

    override suspend fun update(superhero: Superhero) {
        database.update(superhero) //check thread
    }

}