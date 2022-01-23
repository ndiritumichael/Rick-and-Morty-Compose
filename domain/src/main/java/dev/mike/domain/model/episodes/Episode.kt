package dev.mike.domain.model.episodes

data class Episode(
    val air_date: String,
    val characters: List<Int>,
    val episode: String,
    val id: Int,
    val name: String,

)
