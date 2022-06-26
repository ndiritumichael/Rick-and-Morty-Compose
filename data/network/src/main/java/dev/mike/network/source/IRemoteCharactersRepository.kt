package dev.mike.network.source

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto

interface IRemoteCharactersRepository {

    suspend fun getCharacters(
        page: Int,
        name: String? = null
    ): CharactersDto

    suspend fun getCharacterDetails(
        characterId: Int
    ): SingleCharacterDto
}
