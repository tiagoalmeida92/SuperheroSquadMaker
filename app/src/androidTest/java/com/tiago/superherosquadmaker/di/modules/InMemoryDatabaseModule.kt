package com.tiago.superherosquadmaker.di.modules

import android.app.Application
import androidx.room.Room
import com.tiago.superheroes_database.AppDatabase
import com.tiago.superheroes_database.model.SuperheroRoom
import com.tiago.superheroes_domain.Superhero
import com.tiago.superherosquadmaker.bdd.testdata.Superheroes
import com.tiago.superherosquadmaker.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class InMemoryDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        val db = Room.inMemoryDatabaseBuilder(
            application,
            AppDatabase::class.java
        ).build()
        return db
    }
}