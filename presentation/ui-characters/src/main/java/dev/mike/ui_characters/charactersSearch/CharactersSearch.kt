package dev.mike.ui_characters.charactersSearch

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.mike.ui_characters.characterList.CharactersListViewModel
import dev.mike.ui_characters.characterList.components.CharactersListColumn
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun CharactersSearch(
    viewModel: CharacterSearchViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val state = viewModel.searchResult.value

    val characters = state.dataList?.collectAsLazyPagingItems()

    val searchString = viewModel.searchString.collectAsState().value
    LaunchedEffect(key1 = searchString){

        viewModel.searchCharacterbyName(searchString)
    }
    Scaffold(topBar =
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigateUp }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)


            }
            TextField(
                value = viewModel.searchString.collectAsState().value,
                onValueChange = { name ->
                    viewModel.searchCharacter(name)
                },
                placeholder = {
                    Text(text = "Search Characters")
                }
            )

        }

    }) {
        
        characters?.let { searchCharacters ->
            CharactersListColumn(items = searchCharacters)
                
            
            
            
        }
        
        

    }


}