package dev.mike.rick_and_morty_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.mike.common.Constants.common
import dev.mike.commons.ui.theme.RickandMortyComposeTheme
import dev.mike.rick_and_morty_compose.components.AppContent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalMaterialApi::class,
        androidx.compose.animation.ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
        }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            RickandMortyComposeTheme {

                ProvideWindowInsets {

                    AppContent()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name! $common")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickandMortyComposeTheme {
        Greeting("Android")
    }
}
