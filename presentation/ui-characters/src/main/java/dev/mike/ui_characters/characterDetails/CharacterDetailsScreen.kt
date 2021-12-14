package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailsScreen(
    id: Int,
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
                CircularProgressIndicator(modifier = Modifier.size(75.dp))
            }
        }
    }

    detailsState.data?.let {

        Column() {
            Text(text = it.name)
        }
    }
}
