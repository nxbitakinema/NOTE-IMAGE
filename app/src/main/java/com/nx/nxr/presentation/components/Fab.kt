package com.nx.nxr.presentation.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun ButtonFAB(
    contentDescription: String,
    icon: Int,
    action: () -> Unit
) {
    return FloatingActionButton(
        onClick = action,
        backgroundColor = MaterialTheme.colors.error
    ) {
        Icon(
            ImageVector.vectorResource( id = icon ),
            contentDescription = contentDescription,
        )
    }
}