package com.nx.nxr.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.nxr.R
import com.nx.nxr.presentation.components.ButtonFAB
import com.nx.nxr.presentation.components.NxAppbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenCreate (
    appViewModel: AppViewModel = hiltViewModel(),
    onCreated: () -> Unit,
) {
    var currentText by remember { mutableStateOf("") }
    var currentTitle by remember { mutableStateOf("") }
    var currentImage by remember { mutableStateOf("") }
    var saveButtonVisible by remember { mutableStateOf(false) }

    fun setSaveButtonVisibility() {
        saveButtonVisible =
            (currentTitle.isNotEmpty() && currentText.isNotEmpty())
    }

    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            appViewModel.requestUriPermission(uri)
        }
        currentImage = uri.toString()
    }

    Scaffold(
        topBar = {
            NxAppbar(
                title = "create",
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_save),
                        contentDescription = "save",
                    )
                },
                onIconClick = {
                    appViewModel.create(
                        title = currentTitle,
                        text = currentText,
                        imageUri = currentImage
                    )
                    onCreated()
                },
                iconState = saveButtonVisible,
            )
        },
        floatingActionButton = {
            ButtonFAB(
                icon = R.drawable.icon_camera,
                contentDescription = "add photo",
                action = { getImageRequest.launch(arrayOf("image/*")) }
            )
        }
    ) {
        Column(
            Modifier.fillMaxSize().padding(8.dp)
        ) {
            if (currentImage.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = Uri.parse(currentImage))
                            .build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentTitle,
                onValueChange = { value ->
                    currentTitle = value
                    setSaveButtonVisibility()
                },
                label = { Text(text = "title") },
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.primaryVariant,
                    focusedLabelColor = MaterialTheme.colors.primaryVariant
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                value = currentText,
                onValueChange = { value ->
                    currentText = value
                    setSaveButtonVisibility()
                },
                label = { Text(text = "text") },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.primaryVariant,
                    focusedLabelColor = MaterialTheme.colors.primaryVariant
                )
            )
        }
    }
}