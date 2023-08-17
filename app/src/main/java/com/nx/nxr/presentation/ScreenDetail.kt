package com.nx.nxr.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    var screen by remember { mutableStateOf(FakePlaceHolder) }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            screen = appViewModel.getVIEWMODELs(nxrId) ?: FakePlaceHolder
        }
    }

    Scaffold(
        topBar = {
            NxAppbar(
                title = screen.title,
                icon = {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = "edit") },
                onIconClick = { onEditClicked() },
                iconState = true,

            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            screen.let { screen2 ->
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
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(375.dp)
                    )
                }
//                Text(
//                    text = screen2.dateUpdated,
//                    fontSize = 10.sp,
//                    color = Color.Gray,
//                    modifier = Modifier
//                        .padding(start = 8.dp, top = 8.dp, bottom = 6.dp, end = 8.dp)
//                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = screen2.text,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.primaryVariant,
                    lineHeight = 1.7.em,
                    textAlign = TextAlign.Justify,
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }
}




