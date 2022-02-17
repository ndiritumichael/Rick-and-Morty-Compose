package dev.mike.ui_characters.characterList.components.gridview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.transform.CircleCropTransformation
import dev.mike.domain.model.Character
import dev.mike.ui_characters.characterList.components.CharacterInfo
import dev.mike.ui_characters.characterList.components.ImageCard

@Composable
fun GridItem(character: Character) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ImageCard(
                imageLink = character.imageUrl,
                modifier = Modifier.fillMaxWidth(0.7f),
                transformations = listOf(CircleCropTransformation())
            )
            CharacterInfo(character = character, showExtraInfo =  false, alignment = Alignment.CenterHorizontally)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterListGrid(
    items: LazyPagingItems<Character>,
    listState: LazyGridState = rememberLazyGridState(),
    navigate: (Int) -> Unit = {}
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState) {
        items(items.itemCount) { index ->
            items[index]?.let { GridItem(character = it) }
        }
    }
}
