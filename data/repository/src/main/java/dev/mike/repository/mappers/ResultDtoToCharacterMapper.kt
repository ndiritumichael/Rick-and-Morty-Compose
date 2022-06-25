package dev.mike.repository.mappers

import dev.mike.network.models.characters.ResultDto
import dev.mike.network.models.characters.singleCharacter.Location
import dev.mike.network.models.characters.singleCharacter.Origin
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto

fun ResultDto.toSingleCharacter(): SingleCharacterDto {

    return SingleCharacterDto(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = Location(
            location.name,
            location.url
        ),
        name = name,
        origin = Origin(
            origin.name,
            origin.url
        ),
        species = species,
        status = status,
        type = type,
        url = url
    )
}
