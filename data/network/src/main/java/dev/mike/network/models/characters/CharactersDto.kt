package dev.mike.network.models.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<ResultDto>
)
