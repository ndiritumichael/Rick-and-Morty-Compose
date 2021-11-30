package dev.mike.domain.usecases

import androidx.paging.PagingData
import dev.mike.domain.model.Character
import dev.mike.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharactersRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Character>> {
        return characterRepository.getAllCharacters()
    }
}
