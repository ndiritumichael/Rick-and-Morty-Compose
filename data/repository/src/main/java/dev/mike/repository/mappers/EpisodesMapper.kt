package dev.mike.repository.mappers

import android.net.Uri
import dev.mike.domain.model.episodes.Episode
import dev.mike.network.models.episodes.singleepisode.SingleEpisodeDTO


fun SingleEpisodeDTO.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters.map { character ->
            val uri = Uri.parse(character)
            uri.getQueryParameter("character")?.toInt() ?: 1
        },
        episode = episode,
        id = id,
        name = name

    )


}