package dev.mike.network.models.episodes.singleepisode

data class SingleEpisode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)