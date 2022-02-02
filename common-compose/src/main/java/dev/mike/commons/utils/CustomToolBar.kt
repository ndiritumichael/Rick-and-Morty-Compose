package dev.mike.commons.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomToolBar(

    modifier: Modifier = Modifier,
    TitleText: @Composable BoxScope.() -> Unit,
    Icons: @Composable RowScope.() -> Unit,

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        TitleText()

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            painter = painterResource(
                id = dev.mike.commons.R.drawable.showlogo
            ),
            contentDescription = "logo"
        )

        Card(

            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    alignment = Alignment.BottomEnd
                ),
            elevation = 8.dp

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {

                Icons()
            }
        }
    }
}
