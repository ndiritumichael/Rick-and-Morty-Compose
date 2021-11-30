package dev.mike.repository.mappers

import dev.mike.domain.model.Character
import dev.mike.network.models.characters.ResultDto

fun ResultDto.toCharacter(): Character {

    return Character(
        id = id,
        name = name,
        imageUrl = image,
        gender = gender,
        status = status
    )
}
