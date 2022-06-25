package dev.mike.network.data

import dev.mike.network.ApiService
import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import dev.mike.network.source.IRemoteCharactersRepository
import javax.inject.Inject

class RemoteCharactersRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    IRemoteCharactersRepository {
    override suspend fun getCharacters(page: Int, name: String?): CharactersDto {
        return apiService.getCharacters(page, name)
    }

    override suspend fun getCharacterDetails(characterId: Int): SingleCharacterDto {
        return apiService.getCharacterDetails(characterId)
    }
}
