package com.nx.nxr.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nx.nxr.*
import com.nx.nxr.R
import com.nx.nxr.domain.model.NXR
import com.nx.nxr.presentation.components.BoxList
import com.nx.nxr.presentation.components.ButtonFAB
import com.nx.nxr.presentation.components.DeleteDialog
import com.nx.nxr.presentation.components.NxAppbar
import com.nx.nxr.presentation.components.SearchBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenList (
    onCreateClicked: () -> Unit,
    onDetailClick: (noteId: Int) -> Unit,
    appViewModel: AppViewModel = hiltViewModel()
) {

    // -----------------------  Delete dialog ------------------------- //
    var deletePrompt by remember { mutableStateOf("") }
    var notesToDelete by remember { mutableStateOf(listOf<NXR>()) }
    var dialogOpenState by remember { mutableStateOf(false) }
    fun showDeleteDialog(
        nxs: List<NXR>,
        prompt: String
    ) {
        deletePrompt = prompt
        notesToDelete = nxs
        dialogOpenState = true
    }
    // -----------------------  Delete dialog ------------------------- //

    val context = LocalContext.current
    val screen1 by appViewModel.nxs.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            NxAppbar(
                title = "NXR",
                onIconClick = {
                    if (screen1.isNotEmpty()) {
                        showDeleteDialog(
                            nxs = screen1, "Are you sure want to delete all notes"
                        )
                    } else {
                        Toast.makeText( context, "no item found.", Toast.LENGTH_SHORT ).show()
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_delete),
                        contentDescription = "delete",
                        tint = MaterialTheme.colors.background
                    )
                },
                iconState = false
            )
        },
        floatingActionButton = {
            ButtonFAB(
                icon = R.drawable.icon_add,
                contentDescription = "add",
                action = { onCreateClicked() }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChanged = { searchQuery = it }
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            BoxList(
                nxrs = screen1,
                onDeleteNotes = { notesList, prompt ->
                    showDeleteDialog(notesList, prompt)
                },
                onDetailClick = { onDetailClick(it) },
                query = searchQuery,
            )
            if (dialogOpenState) {
                DeleteDialog(
                    text = deletePrompt,
                    cancelPressed = { dialogOpenState = false },
                    deletePressed = {
                        dialogOpenState = false
                        notesToDelete.forEach {
                            appViewModel.delete(it)
                        }
                    }
                )
            }
        }
    }
}





