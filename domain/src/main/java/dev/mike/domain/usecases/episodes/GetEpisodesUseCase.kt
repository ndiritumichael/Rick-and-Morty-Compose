package dev.mike.domain.usecases.episodes

import dev.mike.domain.repositories.episodes.SingleEpisodeRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val episodeRepository: SingleEpisodeRepository) {

    suspend operator fun invoke(id:String) = episodeRepository.getEpisode(id)
}