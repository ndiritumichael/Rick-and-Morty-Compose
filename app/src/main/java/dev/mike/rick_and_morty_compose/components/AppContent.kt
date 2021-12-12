package dev.mike.rick_and_morty_compose.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.mike.rick_and_morty_compose.navigation.MainNavigation
import dev.mike.rick_and_morty_compose.navigation.NavigationItem

@ExperimentalAnimationApi
@Composable
fun AppContent() {
    val navController = rememberNavController()

    val bottomScreens = listOf(
        NavigationItem.Home,
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

@Composable
fun BottomNavigationBar(navHostController: NavHostController, bottomScreens: List<NavigationItem>) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.85F),
        elevation = 10.dp,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {

        BottomNavigation() {
            bottomScreens.map {
                BottomNavigationItem(
                    selected = navHostController
                        .currentBackStackEntryAsState().value?.destination?.route == it.route,
                    onClick = {
                        navHostController.navigate(
                            it.route
                        )
                    },
                    icon = {
                        Icon(painter = painterResource(id = it.icon!!), contentDescription = "bottom Bar Icon")
                    }
                )
            }
        }
    }
}
