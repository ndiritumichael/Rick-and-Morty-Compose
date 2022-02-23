package dev.mike.ui_characters.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.ui_characters.CharactersList
import dev.mike.ui_characters.characterDetails.CharacterDetailsScreen
import dev.mike.ui_characters.charactersSearch.CharactersSearch
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
fun NavGraphBuilder.charactersGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Characters.CHARACTERlIST,
        route = Characters.CHARACTERSGRAPH
    ) {

        composable(
            route = Characters.CHARACTERlIST,
            enterTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->
                slideInVertically(initialOffsetY = { +1000 }, animationSpec = spring())

               // slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->
                // scaleOut(targetScale = .5F)
                shrinkVertically(shrinkTowards = Alignment.Top)

                // slideOutHorizontally(targetOffsetX = { -3000 }, animationSpec = tween(700))
            },
            popEnterTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                slideInVertically(initialOffsetY = { 5000 }, animationSpec = tween(700))

                // slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
            },
            popExitTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
            }

        ) {

            CharactersList({
                navHostController.navigate(Characters.CHARACTERSEARCH)
            }) { characterId ->
                navHostController.navigate(Characters.CHARACTERDETAILS + "/$characterId") {

                    launchSingleTop
                }
            }
        }

        composable(
            enterTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                scaleIn()

                // slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                scaleOut()

                // slideOutVertically(targetOffsetY = { -1000 }, animationSpec = tween(700))
            },
            popEnterTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
            },
            popExitTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
            },
            route = Characters.CHARACTERDETAILS + "/{characterId}",
            arguments = listOf(
                navArgument(
                    name = "characterId",
                ) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            characterId?.let {

                CharacterDetailsScreen(
                    navigateToAllEpisodes = {

                        navHostController.navigate(Episodes.EPISODELIST) {

                            popUpTo(route = Characters.CHARACTERDETAILS) { saveState = true }
                        }
                    },
                    navigateToSpecificEpisode = {
                    },

                    { navHostController.popBackStack() }
                )
            }
        }

        composable(
            enterTransition = { // initial: NavBackStackEntry, target: NavBackStackEntry ->

                slideInVertically(initialOffsetY = { +1000 }, animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy))
            },
            popExitTransition = {
                scaleOut()
                //  slideOutVertically(targetOffsetY = { +3000 }, animationSpec = tween(700))
            },

            route = Characters.CHARACTERSEARCH
        ) {

            CharactersSearch(navigate = {
                characterId ->
                navHostController.navigate(Characters.CHARACTERDETAILS + "/$characterId") {
                    launchSingleTop
                }
            }) {
                navHostController.popBackStack()
            }
        }
    }
}
