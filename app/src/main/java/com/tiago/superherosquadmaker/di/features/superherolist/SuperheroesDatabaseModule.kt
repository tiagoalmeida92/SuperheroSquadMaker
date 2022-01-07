package com.tiago.superherosquadmaker.di.features.superherolist

import com.tiago.superheroes_database.AppDatabase
import com.tiago.superheroes_database.SuperheroDao
import com.tiago.superheroes_database.SuperheroDatabase
import com.tiago.superheroes_database.SuperheroDatabaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SuperheroesDatabaseModule {
    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): SuperheroDao {
        return appDatabase.superheroDao()
    }

    @Singleton
    @Provides
    fun provideSuperheroDatabase(dao: SuperheroDao): SuperheroDatabase {
        return SuperheroDatabase(dao, SuperheroDatabaseMapper())
    }
}