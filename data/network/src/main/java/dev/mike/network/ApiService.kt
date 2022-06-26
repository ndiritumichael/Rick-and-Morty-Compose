package dev.mike.network

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import dev.mike.network.models.episodes.allepisodes.Episodes
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO
import dev.mike.network.utils.CHARACTERSPATH
import dev.mike.network.utils.EPISODEID
import dev.mike.network.utils.EPISODESPATH
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(CHARACTERSPATH)
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name")name: String? = null
    ): CharactersDto

    @GET("$CHARACTERSPATH/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int
    ): SingleCharacterDto

    @GET(EPISODESPATH)
    suspend fun getEpisodes(): Episodes

    @GET("$EPISODEID{episode}")
    suspend fun getEpisode(
        @Path("episode") episode: String
    ): List<SingleEpisodeDTO>

    @GET("$EPISODEID{episode}")
    suspend fun getoneEpisode(
        @Path("episode") episode: String
    ): SingleEpisodeDTO
}
