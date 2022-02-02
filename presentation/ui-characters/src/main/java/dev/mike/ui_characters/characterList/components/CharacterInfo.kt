package dev.mike.ui_characters.characterList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette
import dev.mike.commons.components.MediumSpacer
import dev.mike.domain.model.Character

@Composable
fun CharacterUI(character: Character, onClick: (Int) -> Unit) {

    AnimatedVisibility(

        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Card(
            modifier = Modifier
                .animateContentSize()
                .padding(8.dp)
                .clickable {
                    onClick(character.id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Row() {
                ImageCard(
                    imageLink = character.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                )
                MediumSpacer()
                CharacterInfo(
                    character = character,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun ImageCard(imageLink: String, modifier: Modifier, bitPallette: (Palette) -> Unit = {}) {
    CoilImage(
        imageRequest =
        ImageRequest
            .Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .build(),
        alignment = Alignment.Center,
        loading = {
            // Text(text = "Loading...")
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = true,

                        highlight = PlaceholderHighlight.shimmer(),
                    )
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        circularReveal = CircularReveal(
            duration = 300,
        ),
        modifier = modifier,
        bitmapPalette = BitmapPalette { pallette ->
            bitPallette(pallette)
        }

    )
}

@Composable
fun CharacterInfo(character: Character, modifier: Modifier = Modifier) {
    val color = when (character.status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }
    Column(modifier = modifier.fillMaxHeight()) {
        Text(text = character.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Origin", fontSize = 13.sp)
        Text(text = character.origin)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Status", fontSize = 13.sp)
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(color = color, shape = CircleShape)
            )
            Text(text = character.status + " - " + character.species)
        }
    }
}
