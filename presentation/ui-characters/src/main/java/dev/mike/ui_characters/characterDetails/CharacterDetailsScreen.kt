package dev.mike.ui_characters.characterDetails

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.mike.commons.components.MediumSpacer
import dev.mike.ui_characters.characterList.components.ImageCard
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.min

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun CharacterDetailsScreen(
    navigateToAllEpisodes: () -> Unit,
    navigateToSpecificEpisode: (Int) -> Unit,

    navigate: () -> Unit,

    viewModel: CharacterDetailsViewModel = hiltViewModel(),

) {
    val systemInDarkMode = isSystemInDarkTheme()
    val lazyListState = rememberLazyListState()
    val scrollOffset = min(
        1f.coerceAtMost(1f),
        (1 - (lazyListState.firstVisibleItemScrollOffset / 2000f + lazyListState.firstVisibleItemIndex)).coerceAtLeast(
            0f
        )
    )

    val imageSize by animateFloatAsState(
        targetValue = 0.4f * scrollOffset,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = FastOutSlowInEasing
        )

    )
    val systemUiController = rememberSystemUiController()

    var colorPalette by remember {
        mutableStateOf<Palette?>(null)
    }

    val vibrantColor = when (systemInDarkMode) {

        true -> colorPalette?.darkVibrantSwatch?.rgb
        false -> colorPalette?.lightVibrantSwatch?.rgb
    } ?: 0
    val mutedColor = when (systemInDarkMode) {

        true -> colorPalette?.darkMutedSwatch?.rgb
        false -> colorPalette?.lightMutedSwatch?.rgb
    } ?: 0

    val gradient = Brush.verticalGradient(
        listOf(
            Color(vibrantColor),
            Color(mutedColor)
        )

    )

    val episodesList =
        viewModel.episodesList.collectAsState(initial = CharacterEpisodesState(isLoading = true)).value

    LaunchedEffect(key1 = imageSize, key2 = vibrantColor) {

        systemUiController.setStatusBarColor(
            color = if (imageSize == 0f) Color.Transparent else Color(vibrantColor),
            darkIcons = systemInDarkMode.not()
        )
    }

    val detailsState = viewModel.detailsState.value

    detailsState.errorMessage?.let { Text(text = it) }

    if (detailsState.isLoading) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Card(shape = CircleShape) {

                CircularProgressIndicator(modifier = Modifier.size(60.dp).padding(16.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        detailsState.data?.let { character ->

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(imageSize)
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
                            .graphicsLayer {

                                alpha = min(1f, 1 - (scrollOffset / 600f))
                                translationY = -scrollOffset * 0.1f
                            }
                    ) { palette ->
                        colorPalette = palette
                    }
                }

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

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
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    MediumSpacer()
                    Text(text = character.status, style = MaterialTheme.typography.body1)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            text = character.species,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Species")
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = character.gender,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Gender")
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = character.location,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Location", style = MaterialTheme.typography.subtitle2)
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "view locations"
                        )
                    }
                }

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                    // backgroundColor = Color(mutedColor).copy(0.5f)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        state = lazyListState
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Episodes Appeared In (${episodesList.episodesDataList.size})",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                TextButton(onClick = {
                                    navigateToAllEpisodes()
                                }) {
                                    Text(text = "View All Episodes", fontSize = 13.sp)
                                }
                            }
                        }

                        items(episodesList.episodesDataList) { episode ->
                           /* Card(
                                onClick = {
                                    navigateToSpecificEpisode(episode.id)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp),

                            ) {*/
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                                MediumSpacer()
                                Column {
                                    Text(text = episode.episode)
                                    Text(text = episode.name, fontWeight = FontWeight.Bold)
                                }
                            }
                            // }
                        }
                        if (episodesList.isLoading) {

                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        item {
                            Text(text = episodesList.errorMessage, color = Color.Red)
                        }
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
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

@Preview(showSystemUi = true)
@Composable
fun CharacterMeta(
    header: String = "Interactive Mode",
    description: String = "Trying out Interactive mode in BumbleBee"
) {

    var a by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = header,
            modifier = Modifier.fillMaxWidth(0.4f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        CustomText(text = description)

        CustomText(text = "The value of a: $a ")

        Button(onClick = { a++ }) {
            Text("Click To Add")
        }
    }
}

@Composable
fun CustomText(text: String) {

    Text(
        text,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
}
