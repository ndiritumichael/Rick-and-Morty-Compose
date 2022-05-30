package dev.mike.rick_and_morty_compose.navigation

import androidx.annotation.DrawableRes
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.common.Locations
import dev.mike.common.SETTINGS
import dev.mike.rick_and_morty_compose.R

sealed class NavigationItem(var route: String, var title: String, @DrawableRes var icon: Int) {

    object CharactersScreen : NavigationItem(Characters.CHARACTERlIST, "Characters", R.drawable.ic_baseline_face_24)
    object LocationsScreen : NavigationItem(Locations.LOCATIONlIST, "Locations", R.drawable.ic_baseline_add_location_24)
    object EpisodesScreen : NavigationItem(Episodes.EPISODELIST, "Episodes", R.drawable.ic_baseline_slideshow_24)
    object SettingsScreen : NavigationItem(SETTINGS.SETTINGSGRAPH, "Settings", R.drawable.ic_baseline_settings_24)
}
