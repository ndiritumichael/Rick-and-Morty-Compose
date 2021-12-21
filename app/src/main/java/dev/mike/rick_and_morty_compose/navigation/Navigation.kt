package dev.mike.rick_and_morty_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.common.Locations
import dev.mike.ui_characters.navigation.charactersGraph

@ExperimentalAnimationApi
@Composable
fun MainNavigation(navhostController: NavHostController) {

    AnimatedNavHost(navController = navhostController, startDestination = Characters.CHARACTERSGRAPH) {

        charactersGraph(navhostController)
        composable(
            route = Episodes.EPISODESGRAPH
        ) {
            Text(text = "episode")
        }

        composable(
            route = Locations.LOCATIONSGRAPH
        ) {
            Text(text = "location")
        }
    }
}
