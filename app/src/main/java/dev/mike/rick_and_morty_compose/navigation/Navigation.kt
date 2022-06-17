package dev.mike.rick_and_morty_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.devmike.ui_episodes.navigation.episodesGraph
import com.devmike.ui_locations.locationsGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dev.mike.common.Characters
import dev.mike.ui_characters.navigation.charactersGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun MainNavigation(navhostController: NavHostController, modifier: Modifier = Modifier) {

    AnimatedNavHost(navController = navhostController, startDestination = Characters.CHARACTERSGRAPH, modifier = modifier) {

        charactersGraph(navhostController)
        episodesGraph(navhostController)
        locationsGraph(navhostController)
    }
}
