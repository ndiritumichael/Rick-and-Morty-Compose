package dev.mike.network.models.characters.singleCharacter


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)