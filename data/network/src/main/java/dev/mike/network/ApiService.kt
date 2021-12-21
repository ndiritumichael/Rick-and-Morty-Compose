package dev.mike.network

import dev.mike.network.models.characters.CharactersDto
import dev.mike.network.models.characters.singleCharacter.SingleCharacterDto
import retrofit2.Response
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
        @Path("character_id") character_id: Int
    ): SingleCharacterDto

    @GET("character")
    suspend fun filterCharacters(
        @Query("name") name: String
    ):CharactersDto
}
