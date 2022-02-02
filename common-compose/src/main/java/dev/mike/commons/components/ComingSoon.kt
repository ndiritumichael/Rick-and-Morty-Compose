package dev.mike.commons.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mike.commons.R

@Composable
@Preview(showSystemUi = true)
fun ComingSoonBox(clicked: () -> Unit = {}) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(text = "Coming Soon!!", style = MaterialTheme.typography.h3)
            Image(
                painter = painterResource(id = R.drawable.showlogo),
                contentDescription = null
            )
            Text(text = "Work In Progress", style = MaterialTheme.typography.body1)

            Button(onClick = clicked) {
                Text(text = "Go Back")
            }
        }
    }
}
