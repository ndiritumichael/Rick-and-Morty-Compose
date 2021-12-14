package dev.mike.rick_and_morty_compose.navigation

import androidx.annotation.DrawableRes
import dev.mike.rick_and_morty_compose.R

sealed class NavigationItem(var route: String, var title: String, @DrawableRes var icon: Int) {

    object Characters : NavigationItem("characters", "Characters", R.drawable.ic_ricksvg)
    object Locations : NavigationItem("locations", "Locations", R.drawable.ic_planets)
    object Episodes : NavigationItem("episodes", "Episodes", R.drawable.ic_episode)
}
