package dev.mike.rick_and_morty_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dev.mike.ui_characters.navigation.charactersGraph

@ExperimentalAnimationApi
@Composable
fun MainNavigation(navhostController: NavHostController) {

    AnimatedNavHost(navController = navhostController, startDestination = "characterlist") {

        charactersGraph(navhostController)
        composable(
            route = NavigationItem.Episodes.route
        ) {
            Text(text = "episode")
        }

        composable(
            route = NavigationItem.Locations.route
        ) {
            Text(text = "location")
        }
    }
}
