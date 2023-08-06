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
import com.nx.nxr.core.FakePlaceHolder
import com.nx.nxr.domain.model.NXR
import com.nx.nxr.presentation.components.ButtonFAB
import com.nx.nxr.presentation.components.NxAppbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenEdit(
    nxrId: Int,
    nxViewMode: AppViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var screen3 by remember { mutableStateOf(FakePlaceHolder) }

    var currentText by remember { mutableStateOf(screen3.text) }
    var currentTitle by remember { mutableStateOf(screen3.title) }
    var currentImage by remember { mutableStateOf(screen3.imageUri) }
    var saveButtonVisible by remember { mutableStateOf(false) }

    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                nxViewMode.requestUriPermission(uri)
            }
            currentImage = uri.toString()
            saveButtonVisible = (currentImage != screen3.imageUri)
        }
    )

    fun setSaveButtonVisibility() {
        saveButtonVisible = !(currentTitle == screen3.title
                && currentText == screen3.text
                && currentImage == screen3.imageUri)
    }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            screen3 = nxViewMode.getVIEWMODELs(nxrId) ?: FakePlaceHolder
            currentText = screen3.text
            currentTitle = screen3.title
            currentImage = screen3.imageUri
        }
    }

    Scaffold(
        topBar = {
            NxAppbar(
                title = "edit",
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_save),
                        contentDescription = "save") },
                onIconClick = {
                    nxViewMode.update(
                        NXR(
                            id = screen3.id,
                            text = currentText,
                            title = currentTitle,
                            imageUri = currentImage
                        )
                    )
                    onBackPressed()
                },
                iconState = saveButtonVisible
            )
        },
        floatingActionButton = {
            ButtonFAB(
                contentDescription = "add photo",
                icon = R.drawable.icon_camera,
                action = { getImageRequest.launch(arrayOf("image/*")) },
            )
        }
    ) {
        Column(
            Modifier.fillMaxSize().padding(8.dp)
        ) {
            if ( !currentImage.isNullOrEmpty() ) {
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
            Spacer(modifier = Modifier.padding(4.dp))
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
                    focusedLabelColor = MaterialTheme.colors.primaryVariant,
                ),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1.0f),
                value = currentText,
                onValueChange = { value ->
                    currentText= value
                    setSaveButtonVisibility()
                },
                label = { Text(text = "story") },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.primaryVariant,
                    focusedLabelColor = MaterialTheme.colors.primaryVariant,
                )
            )
        }
    }
}
