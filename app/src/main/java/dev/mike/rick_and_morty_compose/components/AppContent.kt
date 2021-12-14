package dev.mike.rick_and_morty_compose.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.mike.rick_and_morty_compose.navigation.BottomNavigationBar
import dev.mike.rick_and_morty_compose.navigation.MainNavigation
import dev.mike.rick_and_morty_compose.navigation.NavigationItem

@ExperimentalAnimationApi
@Composable
fun AppContent() {
    val navController = rememberAnimatedNavController()

    val bottomScreens = listOf(
        NavigationItem.Characters,
        NavigationItem.Locations,
        NavigationItem.Episodes,
    )

    val showNavBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomScreens.map {
        it.route
    }

    Scaffold(
        bottomBar = {
            if (showNavBar) {
                BottomNavigationBar(navController, bottomScreens)
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            MainNavigation(navhostController = navController)
        }
    }
}
