package dev.mike.network.source

import dev.mike.network.models.episodes.allepisodes.Episodes
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO

interface IEpisodesRepository {

    suspend fun getEpisodes(): Episodes

    suspend fun getEpisode(
        episode: String
    ): List<SingleEpisodeDTO>


    suspend fun getoneEpisode(
 episode: String
    ): SingleEpisodeDTO
}
