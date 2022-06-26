package dev.mike.ui_characters.fakedata

import androidx.paging.PagingData
import com.dev.mike.core_testing.fake.CoreTestingSampleData
import dev.mike.domain.model.Character
import dev.mike.domain.repositories.CharactersRepository
import dev.mike.domain.usecases.GetCharactersUseCase
import dev.mike.repository.mappers.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private class FakeCharactersRepository : CharactersRepository {
    override suspend fun getAllCharacters(name: String?): Flow<PagingData<Character>> {

        return flow {
            PagingData.from(
                CoreTestingSampleData.page1CharactersDto.results.map {
                    it.toCharacter()
                }
            )
        }
    }
}

val fakeCharactersUseCase = GetCharactersUseCase(FakeCharactersRepository())
