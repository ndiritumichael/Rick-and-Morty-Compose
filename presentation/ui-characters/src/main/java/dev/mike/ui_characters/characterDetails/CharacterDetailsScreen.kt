package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    val vibrantcolor = when (isSystemInDarkTheme()) {

        true -> colorPallete?.darkVibrantSwatch?.rgb
        false -> colorPallete?.lightVibrantSwatch?.rgb
    } ?: 0
    val mutedColor = when (isSystemInDarkTheme()) {

        true -> colorPallete?.darkMutedSwatch?.rgb
        false -> colorPallete?.lightMutedSwatch?.rgb
    } ?: 0

    val gradient = Brush.verticalGradient(
        listOf(
            Color(vibrantcolor),
            Color(mutedColor)
        )

    )

    LaunchedEffect(key1 = vibrantcolor) {
        systemuicontroller.setStatusBarColor(color = Color(vibrantcolor))
    }

    /*  LaunchedEffect(key1 = id) {
        viewModel.getCharacterbyId(id)
    }*/

    val detailsState = viewModel.detailsState.value

    detailsState.errorMessage?.let { Text(text = it) }
    when (detailsState.isLoading) {
        true -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Gray
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
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .background(
                            brush = gradient,
                            shape = RoundedCornerShape(
                                bottomStart = 32.dp, bottomEnd = 32.dp
                            ),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    ImageCard(
                        character.image,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                    ) { pallette ->
                        colorPallete = pallette
                    }
                }
                MediumSpacer()

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                MediumSpacer()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = when (character.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    MediumSpacer()
                    Text(text = character.status, style = MaterialTheme.typography.h6)
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
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            }
        }
    }
}
