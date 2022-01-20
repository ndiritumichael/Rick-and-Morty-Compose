package dev.mike.ui_characters

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.mike.ui_characters.characterList.CharactersListViewModel
import dev.mike.ui_characters.characterList.components.CharactersListColumn

@Composable
fun CharactersList(searchScreen: () -> Unit, navigate: (Int) -> Unit) {
    val uiController = rememberSystemUiController()

    uiController.setStatusBarColor(color = Color.Transparent, darkIcons = !isSystemInDarkTheme())

    val viewModel: CharactersListViewModel = hiltViewModel()
    val state = viewModel.characterListState.value

    val characters = state.dataList?.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Characters") }, backgroundColor = MaterialTheme.colors.onPrimary,
                actions = {
                    IconButton(onClick = { searchScreen() }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            )
        }
    ) {

        if (state.errorMessage.isNotEmpty()) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.errorMessage)
            }
        }

        characters?.let { items ->

            CharactersListColumn(items = items) { characterId ->
                navigate(characterId)
            }
        }
    }
}
