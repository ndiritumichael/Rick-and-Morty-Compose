package dev.mike.rick_and_morty_compose.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.common.Locations
import dev.mike.rick_and_morty_compose.navigation.BottomNavigationBar
import dev.mike.rick_and_morty_compose.navigation.MainNavigation
import dev.mike.rick_and_morty_compose.navigation.NavigationItem

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun AppContent() {
    val navController = rememberAnimatedNavController()

    val bottomScreens = listOf(
        Characters.CHARACTERlIST,
        Episodes.EPISODELIST,
        Locations.LOCATIONlIST
    )


    val showNavBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomScreens

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
        ,
        bottomBar = {
            if (showNavBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->

            MainNavigation(navhostController = navController)

    }
}
