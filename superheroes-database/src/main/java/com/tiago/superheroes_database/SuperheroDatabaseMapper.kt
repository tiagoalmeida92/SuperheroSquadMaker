package com.tiago.superheroes_database

import com.tiago.superheroes_database.model.SuperheroRoom
import com.tiago.superheroes_domain.Superhero

class SuperheroDatabaseMapper {

    fun mapToEntity(dbItem: SuperheroRoom): Superhero {
        return Superhero(
            dbItem.id,
            dbItem.name,
            dbItem.description,
            dbItem.imageUrl,
            dbItem.isInSquad
        )
    }

    fun mapToDbItem(superhero: Superhero): SuperheroRoom {
        return SuperheroRoom(
            superhero.id,
            superhero.name,
            superhero.description,
            superhero.imageUrl,
            superhero.isInSquad
        )
    }

}
