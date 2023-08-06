package com.nx.nxr.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.nxr.core.FakePlaceHolder
import com.nx.nxr.presentation.components.NxAppbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenDetail(
    nxrId: Int,
    appViewModel: AppViewModel = hiltViewModel(),
    onEditClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var screen2 by remember { mutableStateOf(FakePlaceHolder) }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            screen2 = appViewModel.getVIEWMODELs(nxrId) ?: FakePlaceHolder
        }
    }
    Scaffold(
        topBar = {
            NxAppbar(
                title = screen2.title,
                icon = {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = "edit") },
                onIconClick = { onEditClicked() },
                iconState = true
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            screen2.let { screen2 ->
                if (!screen2.imageUri.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = Uri.parse(screen2.imageUri))
                                .build()),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
                Text(
                    text = screen2.dateUpdated,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 6.dp, end = 8.dp)
                )
                Text(
                    text = screen2.text,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 1.7.em,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}




