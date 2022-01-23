package dev.mike.domain.repositories.episodes

import androidx.paging.PagingData
import dev.mike.domain.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getAllEpisodes():Flow<PagingData<Episode>>
}

interface SingleEpisodeRepository{
    fun getEpisode(episodeId:Int):Result<Episode>
}

