package com.devmike.ui_episodes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.mike.common.Episodes
import dev.mike.commons.components.ComingSoonBox

fun NavGraphBuilder.episodesGraph(
    navHostController: NavHostController
) {

    navigation(
        Episodes.EPISODELIST,
        route = Episodes.EPISODESGRAPH
    ) {
        composable(Episodes.EPISODELIST) {

            ComingSoonBox{
               navHostController.popBackStack()
            }
        }
    }
}
