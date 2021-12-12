package dev.mike.ui_characters.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import dev.mike.ui_characters.CharactersList
import dev.mike.ui_characters.characterDetails.CharacterDetailsScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.charactersGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = "characters",
        route = "characterlist"
    ) {

        composable(
            route = "characters"
        ) {

            CharactersList { characterId ->
                navHostController.navigate("characterDetails/$characterId")
            }
        }

        composable(
            route = "characterDetails/{characterId}",
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
                    id = characterId
                )
            }
        }
    }
}
