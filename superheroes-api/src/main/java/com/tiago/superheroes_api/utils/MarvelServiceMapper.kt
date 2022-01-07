package com.tiago.superheroes_api.utils

import com.tiago.superheroes_api.responses.MsvCharacter
import com.tiago.superheroes_api.responses.MsvCharactersResponse
import com.tiago.superheroes_domain.Superhero

interface MarvelServiceMapper {
    fun mapToSuperhero(msvCharacter: MsvCharacter): Superhero
}
