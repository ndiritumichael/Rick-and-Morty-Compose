package dev.mike.network.models.episodes.allepisodes


import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO

data class Episodes(
    val info: Info,
    val results: List<SingleEpisodeDTO>
)