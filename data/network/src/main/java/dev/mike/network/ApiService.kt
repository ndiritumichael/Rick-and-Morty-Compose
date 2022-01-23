package dev.mike.network

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import dev.mike.network.models.episodes.allepisodes.Episodes
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
    @Query("name")name:String?= null
    ): CharactersDto

    @GET("character/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int
    ): SingleCharacterDto



    @GET("episodes")
    suspend fun getEpisodes():Episodes

    @GET("episodes/{episode}")
    suspend fun getEpisode(
        @Path("episode") episode:List<Int>
    ):List<SingleEpisodeDTO>
}
