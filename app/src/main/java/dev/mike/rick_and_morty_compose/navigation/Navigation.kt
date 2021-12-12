package dev.mike.rick_and_morty_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.mike.ui_characters.navigation.charactersGraph

@ExperimentalAnimationApi
@Composable
fun MainNavigation(navhostController: NavHostController) {

    NavHost(navController = navhostController, startDestination = "characterlist") {

        charactersGraph(navhostController)
    }
}
