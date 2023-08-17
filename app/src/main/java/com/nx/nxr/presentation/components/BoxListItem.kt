package com.nx.nxr.presentation.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.nxr.domain.model.NXR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxListItem(
    note: NXR,
    onDelete: () -> Unit,
    onDetailClick: (id:Int) -> Unit,
    noteBackGround: Color,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier.height(80.dp)
    ) {
        Column(
            modifier = Modifier
                .background(noteBackGround)
                .fillMaxWidth()
                .fillMaxSize()
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),   // click effect
                    onClick = { onDetailClick(note.id ?: 0) },
                    onLongClick = {
                        if (note.id != 0) {
                            onDelete()
                        }
                    }
                )
        ) {
            Row {
                if ( !note.imageUri.isNullOrEmpty() ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest
                                .Builder(context)
                                .data(Uri.parse(note.imageUri))
                                .build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 2.dp, end = 8.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .height(80.dp)
                            .width(80.dp)
                    )
                }
                Column {
                    Text(
                        text = note.title,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 2.dp, end = 8.dp, bottom = 6.dp),
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                    Text(
                        text = note.text,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle2,
                        lineHeight = 1.5.em,
                        maxLines = 5
                    )
                }
            }
        }
    }
}