package dev.mike.network.data

import dev.mike.network.ApiService
import dev.mike.network.models.episodes.allepisodes.Episodes
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO
import dev.mike.network.source.IEpisodesRepository
import javax.inject.Inject

class RemoteEpisodesImpl @Inject constructor(private val apiService: ApiService) : IEpisodesRepository {
    override suspend fun getEpisodes(): Episodes {
        return apiService.getEpisodes()
    }

    override suspend fun getEpisode(episode: String): List<SingleEpisodeDTO> {
        return apiService.getEpisode(episode)
    }

    override suspend fun getoneEpisode(episode: String): SingleEpisodeDTO {
        return apiService.getoneEpisode(episode)
    }
}
