package dev.mike.repository.fake

import android.content.res.Resources
import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.ResultDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import dev.mike.network.source.IRemoteCharactersRepository
import dev.mike.repository.mappers.toSingleCharacter

class FakeCharactersRepository : IRemoteCharactersRepository {

    private val charactersPages: MutableMap<Int, CharactersDto> = mutableMapOf()

    private var allCharacters: List<ResultDto>

    init {
        charactersPages[1] = SampleData.page1CharactersDto
        charactersPages[2] = SampleData.page2CharactersDto
        charactersPages[3] = SampleData.page3CharactersDto

        allCharacters = buildList {
            SampleData.page1CharactersDto.results
            SampleData.page2CharactersDto.results
            SampleData.page3CharactersDto.results
        }
    }
    override suspend fun getCharacters(page: Int, name: String?): CharactersDto {
        return charactersPages.getOrElse(page) {
            throw NotFoundException()
        }
    }

    override suspend fun getCharacterDetails(characterId: Int): SingleCharacterDto {
        return allCharacters.find { character ->
            character.id == characterId
        }?.toSingleCharacter() ?: throw Resources.NotFoundException()
    }
}
class NotFoundException:Exception("Not Found")


