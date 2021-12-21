package dev.mike.ui_characters.characterList.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.mike.commons.components.DialogCircularProgressBar
import dev.mike.domain.model.Character

@Composable
fun CharactersListColumn(items: LazyPagingItems<Character>,navigate:(Int) -> Unit ={}) {
    LazyColumn {

        items(items) { character ->
                CharacterUI(character = character!!) { id ->

                    navigate(id)

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