package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.mike.commons.components.MediumSpacer
import dev.mike.ui_characters.characterList.components.ImageCard

@Composable
fun CharacterDetailsScreen(

    navigate: () -> Unit,

    viewModel: CharacterDetailsViewModel = hiltViewModel(),

) {
    val systemuicontroller = rememberSystemUiController()
    var colorPallete by remember {
        mutableStateOf<Palette?>(null)
    }

    val statusBarcolor = when (isSystemInDarkTheme()) {

        true -> colorPallete?.darkVibrantSwatch?.rgb
        false -> colorPallete?.lightVibrantSwatch?.rgb
    } ?: 0


  /*  LaunchedEffect(key1 = id) {
        viewModel.getCharacterbyId(id)
    }*/

    val detailsState = viewModel.detailsState.value

    detailsState.errorMessage?.let { Text(text = it) }
    when (detailsState.isLoading) {
        true -> {
            Box(
                modifier = Modifier.fillMaxSize().background(
                    color = Color(statusBarcolor)
                ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        detailsState.data?.let { character ->

            Column(
                modifier = Modifier.fillMaxSize().background(
                    color = Color(statusBarcolor)
                )
            ) {
                ImageCard(character.image, modifier = Modifier.fillMaxHeight(0.4f)) { pallette ->
                    colorPallete = pallette
                }
                MediumSpacer()
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .alpha(0.7f),
            shape = CircleShape
        ) {
            IconButton(onClick = navigate, modifier = Modifier.padding(4.dp)) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "navigate back")
            }
        }
    }
}
