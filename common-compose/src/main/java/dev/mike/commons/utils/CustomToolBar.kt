package dev.mike.commons.utils

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mike.commons.components.MediumSpacer

@Composable
fun CustomToolBar(
    modifier: Modifier = Modifier,
    TitleText: @Composable BoxScope.() -> Unit,
    icon1: @Composable () -> Unit,
    icon2: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TitleText()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    alignment = Alignment.BottomEnd
                ),
            horizontalArrangement = Arrangement.End
        ) {
            icon1()
            MediumSpacer()
            icon2()
        }
    }
}
