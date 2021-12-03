package dev.mike.network

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersDto

    @GET("characters/{character_id}")
    suspend fun getCharacterDetails(
        @Query("character_id") character_id: Int
    ): Response<SingleCharacterDto>
}
