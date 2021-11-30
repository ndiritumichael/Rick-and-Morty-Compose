package dev.mike.domain.repositories

import androidx.paging.PagingData
import dev.mike.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(): Flow<PagingData<Character>>
}
