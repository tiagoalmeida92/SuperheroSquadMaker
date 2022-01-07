package com.tiago.superherosquadmaker.di.features.superherolist

import com.tiago.superheroes_api.SuperheroService
import com.tiago.superheroes_data.SuperheroRepositoryImpl
import com.tiago.superheroes_database.SuperheroDatabase
import com.tiago.superheroes_domain.repository.SuperheroRepository
import com.tiago.superheroes_domain.usecases.FireSuperhero
import com.tiago.superheroes_domain.usecases.GetSquad
import com.tiago.superheroes_domain.usecases.GetSuperheroes
import com.tiago.superheroes_domain.usecases.HireSuperhero
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class SuperheroesModule {

    @Provides
    fun providesSuperheroesRepository(
        database: SuperheroDatabase,
        service: SuperheroService
    ): SuperheroRepository {
        return SuperheroRepositoryImpl(database, service)
    }

    @Provides
    fun providesGetList(
        superheroRepository: SuperheroRepository
    ): GetSuperheroes {
        return GetSuperheroes(superheroRepository)
    }

    @Provides
    fun providesGetSquad(
        superheroRepository: SuperheroRepository
    ): GetSquad {
        return GetSquad(superheroRepository)
    }

    @Provides
    fun providesFireSuperhero(
        superheroRepository: SuperheroRepository
    ): FireSuperhero {
        return FireSuperhero(superheroRepository)
    }

    @Provides
    fun providesHireSuperhero(
        superheroRepository: SuperheroRepository
    ): HireSuperhero {
        return HireSuperhero(superheroRepository)
    }

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}