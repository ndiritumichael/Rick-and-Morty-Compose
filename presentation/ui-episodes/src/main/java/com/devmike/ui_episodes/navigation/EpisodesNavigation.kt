package com.devmike.ui_episodes.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import dev.mike.common.Episodes
import dev.mike.commons.components.ComingSoonBox
import dev.mike.commons.utils.ResetSystemBars

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.episodesGraph(
    navHostController: NavHostController
) {

    navigation(
        Episodes.EPISODELIST,
        route = Episodes.EPISODESGRAPH
    ) {
        composable(Episodes.EPISODELIST) {
            ResetSystemBars()

            ComingSoonBox("Episodes") {
                navHostController.popBackStack()
            }
        }
    }
}
