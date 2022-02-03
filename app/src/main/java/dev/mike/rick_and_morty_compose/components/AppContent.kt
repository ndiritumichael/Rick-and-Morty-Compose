package dev.mike.rick_and_morty_compose.components

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.mike.common.Characters
import dev.mike.common.Episodes
import dev.mike.common.Locations
import dev.mike.rick_and_morty_compose.navigation.BottomNavigationBar
import dev.mike.rick_and_morty_compose.navigation.MainNavigation

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
            .navigationBarsPadding(),
        bottomBar = {
            if (showNavBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->
        /*navController.addOnDestinationChangedListener { navcontroller, destination, bundle ->
            val previousdestination = navController.previousBackStackEntry?.destination?.route

            Log.d("navigate", "$previousdestination")
            if (previousdestination == "${Characters.CHARACTERDETAILS}/{characterId}") {

                uiController.setStatusBarColor(color = Color.Transparent, darkIcons = darkIcons)
            }
        }*/

        MainNavigation(navhostController = navController)
    }
}
