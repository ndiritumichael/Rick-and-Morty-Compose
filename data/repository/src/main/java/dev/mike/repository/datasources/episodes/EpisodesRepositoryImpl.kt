package dev.mike.repository.datasources.episodes

import dev.mike.domain.model.episodes.Episode
import dev.mike.domain.repositories.episodes.SingleEpisodeRepository
import dev.mike.network.source.IEpisodesRepository
import dev.mike.repository.mappers.toEpisode
import dev.mike.repository.utils.BaseRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(private val repository: IEpisodesRepository) :
    SingleEpisodeRepository, BaseRepository() {

    override suspend fun getEpisode(episodeId: String): Result<List<Episode>> {

        return when {

            // forced to this because when only one episode is passed the api returns an Episode Entity instead of a list.
            // better implementations are welcomed

            episodeId.length > 2 -> {
                safeApiCall {
                    repository.getEpisode(episodeId).map { singleEpisodeDTO ->
                        singleEpisodeDTO.toEpisode()
                    }
                }
            }

            else -> {
                safeApiCall {

                    listOf(
                        repository.getoneEpisode(episodeId).toEpisode()
                    )
                }
            }
        }
    }
}
