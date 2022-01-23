package dev.mike.repository.mappers

import android.net.Uri
import dev.mike.domain.model.Character
import dev.mike.domain.model.CharacterDetails
import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.ResultDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto

fun ResultDto.toCharacter(): Character {

    return Character(
        id = id,
        name = name,
        imageUrl = image,
        gender = gender,
        status = status,
        origin = origin.name,
        species = species

    )
}

fun SingleCharacterDto.toCharacter(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status,
        episode = episode.map { episode ->
            val uri = Uri.parse(episode)
            uri.getQueryParameter("episode")?.toInt() ?: 1

        },
        location = location.name,
        origin = origin.name,
        species = species,
        type = type
    )

}
