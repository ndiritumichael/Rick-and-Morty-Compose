package com.devmike.ui_locations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import dev.mike.common.Locations
import dev.mike.commons.components.ComingSoonBox
import dev.mike.commons.utils.ResetSystemBars

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.locationsGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Locations.LOCATIONlIST,
        route = Locations.LOCATIONSGRAPH
    ) {

        composable(route = Locations.LOCATIONlIST) {
            ResetSystemBars()

            ComingSoonBox("Locations") {
                navHostController.popBackStack()
            }
        }
    }
}
