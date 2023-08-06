package com.nx.nxr.presentation.components

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun NxAppbar(
    title: String,
    onIconClick: (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    iconState: Boolean,
    modifier: Modifier = Modifier

) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        title = { Text(text = title) },
        actions = {
            if (icon != null) {
                IconButton(
                    enabled = iconState,
                    onClick = { onIconClick?.invoke() }
                ) {
                    icon()
                }
            }
        }
    )
}