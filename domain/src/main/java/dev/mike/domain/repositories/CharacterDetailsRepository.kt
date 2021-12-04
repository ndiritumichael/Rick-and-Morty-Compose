package dev.mike.domain.repositories

import dev.mike.domain.model.CharacterDetails


interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(id: Int): Result<CharacterDetails>
}
