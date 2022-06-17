package dev.mike.ui_characters.characterList.components.gridview

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
fun GridItem(character: Character, modifier: Modifier = Modifier, onClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(8.dp)
            .clickable {
                onClick(character.id)
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ImageCard(
                imageLink = character.imageUrl,
                modifier = Modifier.fillMaxWidth(0.7f),
                transformations = listOf(CircleCropTransformation())
            )
            CharacterInfo(character = character, showExtraInfo = false, alignment = Alignment.CenterHorizontally)
        }
    }
}

@Composable
fun CharacterListGrid(
    items: LazyPagingItems<Character>,
    listState: LazyGridState = rememberLazyGridState(),
    navigate: (Int) -> Unit = {}
) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {

        items(items.itemCount) { index ->
            items[index]?.let { character ->
                GridItem(character = character, modifier = Modifier.animateContentSize()) {
                    navigate(it)
                }
            }
        }
    }
}
