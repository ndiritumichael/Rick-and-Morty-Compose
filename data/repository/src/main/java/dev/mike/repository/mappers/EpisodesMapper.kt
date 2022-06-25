package dev.mike.repository.mappers

import dev.mike.domain.model.episodes.Episode
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO
import dev.mike.repository.utils.getPageIntFromUrl


fun SingleEpisodeDTO.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters.map { character ->
          getPageIntFromUrl(character)?: 1
        },
        episode = episode,
        id = id,
        name = name

    )


}