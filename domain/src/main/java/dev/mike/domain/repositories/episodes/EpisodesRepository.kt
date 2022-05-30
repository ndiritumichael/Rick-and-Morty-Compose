package dev.mike.domain.repositories.episodes

import androidx.paging.PagingData
import dev.mike.domain.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    suspend fun getAllEpisodes(): Flow<PagingData<Episode>>
}

interface SingleEpisodeRepository {
    suspend fun getEpisode(episodeId: String): Result<List<Episode>>
}
