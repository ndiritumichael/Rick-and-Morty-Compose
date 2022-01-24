package dev.mike.repository.mappers

import android.net.Uri
import dev.mike.domain.model.episodes.Episode
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO
import dev.mike.repository.utils.getIntFromUrl


fun SingleEpisodeDTO.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters.map { character ->
          getIntFromUrl(character)?: 1
        },
        episode = episode,
        id = id,
        name = name

    )


}