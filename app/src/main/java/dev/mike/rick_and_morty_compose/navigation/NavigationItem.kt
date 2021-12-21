package dev.mike.rick_and_morty_compose.navigation

import androidx.annotation.DrawableRes
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.common.Locations
import dev.mike.rick_and_morty_compose.R

sealed class NavigationItem(var route: String, var title: String, @DrawableRes var icon: Int) {

    object CharactersScreen : NavigationItem(Characters.CHARACTERSGRAPH, "Characters", R.drawable.ic_ricksvg)
    object LocationsScreen : NavigationItem(Locations.LOCATIONSGRAPH, "Locations", R.drawable.ic_planets)
    object EpisodesScreen : NavigationItem(Episodes.EPISODESGRAPH, "Episodes", R.drawable.ic_episode)
}
