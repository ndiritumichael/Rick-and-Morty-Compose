package dev.mike.repository.fake

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.LocationDto
import dev.mike.network.models.characters.Origin
import dev.mike.network.models.characters.ResultDto
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileInputStream

val testjson = Json { ignoreUnknownKeys = true }
object FakeDTOs {

    val characterspage1 = String(FileInputStream("src/test/raw/characterspage1.json").readBytes())
    val characterspage2 = String(FileInputStream("src/test/raw/characterspage2.json").readBytes())
    val characterspage3 = String(FileInputStream("src/main/res/raw/characterspage3.json").readBytes())

    val episodespage1 = String(FileInputStream("src/test/raw/episodespage1.json").readBytes())

    val mortyDetails = String(FileInputStream("src/test/raw/mortydetails.json").readBytes())
    val rickDetails = String(FileInputStream("src/test/raw/ricksanchezdetails.json").readBytes())
    val episode28 = String(FileInputStream("src/test/raw/episode28.json").readBytes())
}

object SampleData {
    val episode28 = testjson.decodeFromString<SingleEpisodeDTO>(FakeDTOs.episode28)

    val page1CharactersDto = testjson.decodeFromString<CharactersDto>(FakeDTOs.characterspage1)
    val page2CharactersDto = testjson.decodeFromString<CharactersDto>(FakeDTOs.characterspage2)
    val page3CharactersDto = testjson.decodeFromString<CharactersDto>(FakeDTOs.characterspage3)

    val page1CharacterDTO = ResultDto(
        created = "2017-11-04T18:50:21.651Z",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3",
            "https://rickandmortyapi.com/api/episode/4",
            "https://rickandmortyapi.com/api/episode/5",
            "https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8",
            "https://rickandmortyapi.com/api/episode/9",
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/11",
            "https://rickandmortyapi.com/api/episode/12",
            "https://rickandmortyapi.com/api/episode/13",
            "https://rickandmortyapi.com/api/episode/14",
            "https://rickandmortyapi.com/api/episode/15",
            "https://rickandmortyapi.com/api/episode/16",
            "https://rickandmortyapi.com/api/episode/17",
            "https://rickandmortyapi.com/api/episode/18",
            "https://rickandmortyapi.com/api/episode/19",
            "https://rickandmortyapi.com/api/episode/20",
            "https://rickandmortyapi.com/api/episode/21",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/23",
            "https://rickandmortyapi.com/api/episode/24",
            "https://rickandmortyapi.com/api/episode/25",
            "https://rickandmortyapi.com/api/episode/26",
            "https://rickandmortyapi.com/api/episode/27",
            "https://rickandmortyapi.com/api/episode/28",
            "https://rickandmortyapi.com/api/episode/29",
            "https://rickandmortyapi.com/api/episode/30",
            "https://rickandmortyapi.com/api/episode/31",
            "https://rickandmortyapi.com/api/episode/32",
            "https://rickandmortyapi.com/api/episode/33",
            "https://rickandmortyapi.com/api/episode/34",
            "https://rickandmortyapi.com/api/episode/35",
            "https://rickandmortyapi.com/api/episode/36",
            "https://rickandmortyapi.com/api/episode/37",
            "https://rickandmortyapi.com/api/episode/38",
            "https://rickandmortyapi.com/api/episode/39",
            "https://rickandmortyapi.com/api/episode/40",
            "https://rickandmortyapi.com/api/episode/41",
            "https://rickandmortyapi.com/api/episode/42",
            "https://rickandmortyapi.com/api/episode/43",
            "https://rickandmortyapi.com/api/episode/44",
            "https://rickandmortyapi.com/api/episode/45",
            "https://rickandmortyapi.com/api/episode/46",
            "https://rickandmortyapi.com/api/episode/47",
            "https://rickandmortyapi.com/api/episode/48",
            "https://rickandmortyapi.com/api/episode/49",
            "https://rickandmortyapi.com/api/episode/50",
            "https://rickandmortyapi.com/api/episode/51"
        ),
        gender = "Male",
        id = 2,
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        location = LocationDto(
            name = "Citadel of Ricks",
            url = "https://rickandmortyapi.com/api/location/3"
        ),
        name = "Morty Smith",
        origin = Origin(
            name = "unknown",
            url = ""
        ),
        species = "Human",
        type = "",
        status = "Alive",
        url = "https://rickandmortyapi.com/api/character/2"
    )
}
