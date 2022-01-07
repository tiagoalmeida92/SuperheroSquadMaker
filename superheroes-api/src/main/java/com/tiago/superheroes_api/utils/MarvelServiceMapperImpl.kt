package com.tiago.superheroes_api.utils

import com.tiago.superheroes_api.responses.MsvCharacter
import com.tiago.superheroes_domain.Superhero

class MarvelServiceMapperImpl : MarvelServiceMapper {
    override fun mapToSuperhero(msvCharacter: MsvCharacter): Superhero {
        val msvThumbnail = msvCharacter.thumbnail
        val thumbnailUri = "${msvThumbnail.path}.${msvThumbnail.extension}"
        return Superhero(
            msvCharacter.id.toInt(),
            msvCharacter.name,
            msvCharacter.description,
            thumbnailUri
        )
    }
}
