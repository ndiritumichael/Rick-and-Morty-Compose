package dev.mike.domain.repositories

import dev.mike.domain.model.CharacterDetails
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(id: Int): Flow<Result<CharacterDetails>>
}
