package dev.mike.ui_characters.characterDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.mike.commons.components.MediumSpacer
import dev.mike.ui_characters.characterList.components.ImageCard

@Composable
fun CharacterDetailsScreen(

    navigate: () -> Unit,

    viewModel: CharacterDetailsViewModel = hiltViewModel(),

) {
    val systemUiController = rememberSystemUiController()

    var colorPalette by remember {
        mutableStateOf<Palette?>(null)
    }

    val vibrantColor = when (isSystemInDarkTheme()) {

        true -> colorPalette?.darkVibrantSwatch?.rgb
        false -> colorPalette?.lightVibrantSwatch?.rgb
    } ?: 0
    val mutedColor = when (isSystemInDarkTheme()) {

        true -> colorPalette?.darkMutedSwatch?.rgb
        false -> colorPalette?.lightMutedSwatch?.rgb
    } ?: 0

    val gradient = Brush.verticalGradient(
        listOf(
            Color(vibrantColor),
            Color(mutedColor)
        )

    )

    LaunchedEffect(key1 = vibrantColor) {
        systemUiController.setStatusBarColor(color = Color(vibrantColor))
    }



    val detailsState = viewModel.detailsState.value

    detailsState.errorMessage?.let { Text(text = it) }
    when (detailsState.isLoading) {
        true -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Gray
                    ),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        detailsState.data?.let { character ->

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .background(
                            brush = gradient,
                            shape = RoundedCornerShape(
                                bottomStart = 32.dp, bottomEnd = 32.dp
                            ),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    ImageCard(
                        character.image,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                    ) { palette ->
                        colorPalette = palette
                    }
                }


                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = when (character.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    MediumSpacer()
                    Text(text = character.status, style = MaterialTheme.typography.body1)


                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(text = character.species,style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                        Text(text ="Species" )

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = character.gender,style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                        Text(text ="Gender" )


                    }

                }

                Row(modifier =Modifier
                    .padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                    Column(


                    ) {

                        Text(
                            text = character.location,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Location", style = MaterialTheme.typography.subtitle2)

                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "view locations")

                    }
                }
                
                
                
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    ,
                shape = CircleShape
            ) {
                IconButton(onClick = navigate, modifier = Modifier.padding(4.dp)) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            }
        }
    }
}


@Composable
fun CharacterMeta(header:String,description:String){
    
    Row(modifier = Modifier.fillMaxWidth()) {
        
        Text(text = header,modifier = Modifier.fillMaxWidth(0.4f), style = MaterialTheme.typography.body1)
        Text(text = description,modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.h5)

    }
    
    
}
