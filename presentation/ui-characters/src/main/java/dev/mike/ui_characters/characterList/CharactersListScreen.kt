package dev.mike.ui_characters

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
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
import dev.mike.ui_characters.characterList.CharactersListViewModel
import dev.mike.ui_characters.characterList.components.CharacterUI

@Composable
fun CharactersList(navigate: (Int) -> Unit) {

    val viewModel: CharactersListViewModel = hiltViewModel()
    val state = viewModel.characterListState.value

    val characters = state.dataList?.collectAsLazyPagingItems()

    if (state.errorMessage.isNotEmpty()) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.errorMessage)
        }
    }

    characters?.let { items ->

        LazyColumn {
            items(items) { item ->

                item?.let { character ->
                    CharacterUI(character = character) { id ->

                        navigate(id)

                    }


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
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.height(30.dp))
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = items.loadState.refresh as LoadState.Error
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = errorMessage.error.localizedMessage!!)
                                    Button(onClick = { retry() }) {
                                        Text(text = "Try Again")

                                    }
                                }
                            }


                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val errorMessage = items.loadState.append as LoadState.Error

                       item {
                           Box(
                               modifier = Modifier.fillMaxWidth(),
                               contentAlignment = Alignment.BottomCenter
                           ) {
                               Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                   Text(text = errorMessage.error.localizedMessage!!)
                                   Button(onClick = { retry() }) {
                                       Text(text = "Try Again")

                                   }
                               }
                           }
                       }
                    }
                }
            }
        }
    }
}
