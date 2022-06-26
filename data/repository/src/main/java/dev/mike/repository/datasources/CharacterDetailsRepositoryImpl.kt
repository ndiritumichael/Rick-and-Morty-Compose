package dev.mike.repository.datasources

import dev.mike.domain.model.CharacterDetails
import dev.mike.domain.repositories.CharacterDetailsRepository
import dev.mike.network.source.IRemoteCharactersRepository
import dev.mike.repository.mappers.toCharacter
import dev.mike.repository.utils.BaseRepository
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val repository: IRemoteCharactersRepository
) : CharacterDetailsRepository, BaseRepository() {
    override suspend fun getCharacterDetails(id: Int): Result<CharacterDetails> {
        // return flow { Result.success(apiService.getCharacterDetails(id).body()!!.toCharacter()) }

        return safeApiCall {
            repository.getCharacterDetails(id).toCharacter()
        }
    }
}
