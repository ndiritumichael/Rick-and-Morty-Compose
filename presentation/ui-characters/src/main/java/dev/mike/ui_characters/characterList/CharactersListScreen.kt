package dev.mike.ui_characters

import android.widget.Toast
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dev.mike.commons.components.MediumSpacer
import dev.mike.commons.utils.CustomToolBar
import dev.mike.commons.utils.ResetSystemBars
import dev.mike.ui_characters.characterList.CharactersListViewModel
import dev.mike.ui_characters.characterList.components.CharactersListColumn
import kotlin.math.min

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharactersList(searchScreen: () -> Unit, navigate: (Int) -> Unit) {
    //ResetSystemBars()

    val context = LocalContext.current

    val viewModel: CharactersListViewModel = hiltViewModel()
    val state = viewModel.characterListState.value
    val lazyListState = rememberLazyListState()
    val scrollOffset = min(
        1f.coerceAtMost(1f),
        (1 - (lazyListState.firstVisibleItemScrollOffset / 2000f + lazyListState.firstVisibleItemIndex)).coerceAtLeast(
            0f
        )
    )

    val toolBarHeight by animateDpAsState(
        targetValue = max(56.dp, 300.dp * scrollOffset),
        animationSpec = tween(easing = FastOutLinearInEasing)
    )

    val characters = state.dataList?.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            if (toolBarHeight > 56.dp) {
                CustomToolBar(

                    modifier = Modifier.height(toolBarHeight),
                    TitleText = {
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h3,

                            text = "Characters",
                            modifier = Modifier
                                .align(
                                    alignment = Alignment.TopStart
                                )
                        )
                    }
                ) {

                    TextButton(
                        onClick = searchScreen,
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(9f)
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                        MediumSpacer()
                        Text(
                            text = "Search Characters",
                            fontStyle = FontStyle.Italic,

                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.h6
                        )
                    }

/* IconButton(
                        modifier = Modifier
                            .weight(9f)
                            ,
                        onClick = { searchScreen() }
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }*/

                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Settings Screen Clicked", Toast.LENGTH_LONG).show()
                        }

                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "settings"
                        )
                    }
                }
            } else {

                TopAppBar(
                    modifier = Modifier.height(toolBarHeight),
                    title = { Text(text = "Characters") },
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    actions = {

                        IconButton(

                            onClick = { searchScreen() }
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "settings"
                            )
                        }
                    }
                )
            }
        }
    ) {

        if (state.errorMessage.isNotEmpty()) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.errorMessage)
            }
        }

        characters?.let { items ->

            CharactersListColumn(items = items, listState = lazyListState) { characterId ->
                navigate(characterId)
            }
        }
    }
}
