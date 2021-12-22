package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.mike.ui_characters.characterList.components.ImageCard

@Composable
fun CharacterDetailsScreen(
    id: Int,
    navigate: () -> Unit,

    viewModel: CharacterDetailsViewModel = hiltViewModel(),

) {
    LaunchedEffect(key1 = id) {
        viewModel.getCharacterbyId(id)
    }

    val detailsState = viewModel.detailsState.value

    detailsState.errorMessage?.let { Text(text = it) }
    when (detailsState.isLoading) {
        true -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        detailsState.data?.let { character ->

            Column(modifier = Modifier.fillMaxSize()) {
                ImageCard(character.image, modifier = Modifier.fillMaxHeight(0.4f))
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
