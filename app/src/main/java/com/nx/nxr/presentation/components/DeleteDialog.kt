package com.nx.nxr.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DeleteDialog(
    text: String,
    deletePressed: () -> Unit,
    cancelPressed: () -> Unit = { }
) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("delete") },
            text = { Text(text) },
            dismissButton = {
                TextButton(onClick = cancelPressed) {
                    Text("cancel")
                }
            },
            confirmButton = {
                TextButton(onClick = deletePressed) {
                    Text("delete")
                }
            }
        )
    }
}

