package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: CharacterDetailsViewModel = hiltViewModel(),

) {
    LaunchedEffect(key1 = id) {
        viewModel.getCharacterbyId(id)
    }

    val detailsState = viewModel.detailsState.value

    Text(text = detailsState.errorMessage)

    detailsState.data?.let {

        Column() {
            Text(text = it.name)
        }
    }
}
