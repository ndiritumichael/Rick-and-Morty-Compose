package dev.mike.ui_characters.charactersSearch

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.mike.commons.components.CustomSearchBar
import dev.mike.commons.utils.ResetSystemBars
import dev.mike.ui_characters.characterList.components.CharactersListColumn
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun CharactersSearch(
    viewModel: CharacterSearchViewModel = hiltViewModel(),
    navigate: (Int) -> Unit,
    navigateUp: () -> Unit
) {
    val state = viewModel.searchResult.value

    val characters = state.dataList?.collectAsLazyPagingItems()

    val searchString = viewModel.searchString.collectAsState().value
    LaunchedEffect(key1 = searchString) {
        if (searchString != "") {

            viewModel.searchCharacterbyName(searchString)
        }
    }
    ResetSystemBars()
    Scaffold(
        topBar =
        {
            CustomSearchBar(
                value = searchString,
                placeholder = "Search Characters",
                navigateUp = navigateUp,
                onValueChange = { name ->
                    viewModel.searchCharacter(name)
                }
            )
        }
    ) {

        characters?.let { searchCharacters ->
            CharactersListColumn(items = searchCharacters) { id ->
                navigate(id)
            }
        }
    }
}
