package dev.mike.rick_and_morty_compose.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    val bottomScreens = listOf(
        NavigationItem.CharactersScreen,
        NavigationItem.LocationsScreen,
        NavigationItem.EpisodesScreen,
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.95F)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 8.dp

    ) {

        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            bottomScreens.map {
                val isSelected = navHostController
                    .currentBackStackEntryAsState().value?.destination?.route == it.route
                Log.d(
                    "bottomnav",
                    "${navHostController
                        .currentBackStackEntryAsState().value?.destination?.route}"
                )

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        navHostController.navigate(
                            it.route
                        ) {
                            restoreState = true
                            launchSingleTop = true

                            val destination = navHostController.graph.findStartDestination().id
                            popUpTo(destination) { saveState = true }
                        }
                    },
                    icon = {
                        Icon(painter = painterResource(id = it.icon), contentDescription = "bottom Bar Icon", modifier = Modifier.size(24.dp))
                    },
                    label = {
                        Text(text = it.title,)
                    },

                    unselectedContentColor = Color.Gray,
                    selectedContentColor = MaterialTheme.colors.primary
                )
            }
        }
    }
}
