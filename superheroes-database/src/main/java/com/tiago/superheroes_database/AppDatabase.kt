package com.tiago.superheroes_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tiago.superheroes_database.model.SuperheroRoom

@Database(entities = [SuperheroRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun superheroDao(): SuperheroDao
}