package dev.mike.network.models.episodes.allepisodes

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Int?
)