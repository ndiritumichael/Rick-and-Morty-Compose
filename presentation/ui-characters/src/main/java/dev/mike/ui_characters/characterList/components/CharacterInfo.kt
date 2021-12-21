package dev.mike.ui_characters.characterList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import dev.mike.domain.model.Character

@Composable
fun CharacterUI(character: Character,onClick:(Int)-> Unit) {
    AnimatedVisibility(visible = true) {
        Card(modifier = Modifier.padding(4.dp).clickable {
            onClick(character.id)
        }, shape = RoundedCornerShape(8.dp),) {
            Row() {
                ImageCard(imageLink = character.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth(0.5f))
                CharacterInfo(character = character)
            }
        }

    }
}


@Composable
fun ImageCard(imageLink: String, modifier: Modifier) {
    CoilImage(
        imageRequest =
        ImageRequest
            .Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .build(),
        alignment = Alignment.Center,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        },
        circularReveal = CircularReveal(
            duration = 300,
        ),
        modifier = modifier
    )
}

@Composable
fun CharacterInfo(character: Character, modifier: Modifier = Modifier) {
    val color = when (character.status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }
    Column(modifier = modifier) {
        Text(text = character.name, fontWeight = FontWeight.Bold)
        Text(text = "Origin: ${character.origin}")
        Row() {
            Box(
                modifier = Modifier
                    .size(3.dp)
                    .background(color = color)
            )
            Text(text = character.status + " - " + character.species)

        }

    }
}
