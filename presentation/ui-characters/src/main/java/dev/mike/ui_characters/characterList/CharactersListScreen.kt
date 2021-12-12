package dev.mike.ui_characters

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.mike.commons.components.DialogCircularProgressBar
import dev.mike.ui_characters.characterDetails.CharacterDetailsScreen

import dev.mike.ui_characters.characterList.CharactersListViewModel

@Composable
fun CharactersList() {
    val context = LocalContext.current
    val viewModel: CharactersListViewModel = hiltViewModel()
    val state = viewModel.characterListState.value

    val characters = state.dataList?.collectAsLazyPagingItems()
    var show by remember {
        mutableStateOf(false)
    }

    if (show) {
        CharacterDetailsScreen(id = 2)
    }

    if (state.errorMessage.isNotEmpty()) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.errorMessage)
        }
    }

    characters?.let { items ->

        LazyColumn {
            items(items) { item ->

                item?.let {

                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                show = true
                            }
                    )
                }
            }

            items.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            DialogCircularProgressBar()
                        }
                    }

                    loadState.append is LoadState.Loading -> {

                        item {
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(modifier = Modifier.height(30.dp))
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = items.loadState.refresh as LoadState.Error
                        item {

                            Toast.makeText(context, errorMessage.error.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val errorMessage = items.loadState.append as LoadState.Error

                        Toast.makeText(context, errorMessage.error.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
