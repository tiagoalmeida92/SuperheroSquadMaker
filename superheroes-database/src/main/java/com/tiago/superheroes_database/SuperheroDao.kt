package com.tiago.superheroes_database

import androidx.room.*
import com.tiago.superheroes_database.model.PartialSuperheroRoom
import com.tiago.superheroes_database.model.SuperheroRoom
import kotlinx.coroutines.flow.Flow


@Dao
interface SuperheroDao {

    @Query("SELECT * FROM superheroRoom")
    fun getAll(): Flow<List<SuperheroRoom>>

    @Query("SELECT * FROM superheroRoom WHERE isInSquad = 1")
    fun getInSquad(): Flow<List<SuperheroRoom>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(superheroes: List<SuperheroRoom>): List<Long>

    @Transaction
    fun upsert(list: List<SuperheroRoom>) {
        val insertResult: List<Long> = insertAll(list)
        val updateList: MutableList<SuperheroRoom> = ArrayList()
        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) {
                updateList.add(list[i])
            }
        }
        if (updateList.isNotEmpty()) {
            partialUpdate(updateList.map {
                PartialSuperheroRoom(
                    it.id,
                    it.name, it.description, it.imageUrl
                )
            })
        }
    }

    @Update
    fun update(superheroRoom: SuperheroRoom)

    @Update(entity = SuperheroRoom::class)
    fun partialUpdate(list: List<PartialSuperheroRoom>)

}