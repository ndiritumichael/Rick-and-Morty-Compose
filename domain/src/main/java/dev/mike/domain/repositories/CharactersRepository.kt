package dev.mike.domain.repositories

import androidx.paging.PagingData
import dev.mike.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(name: String? = null): Flow<PagingData<Character>>
}
