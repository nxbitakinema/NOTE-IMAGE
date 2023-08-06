package com.nx.nxr.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nx.nxr.core.getDay
import com.nx.nxr.domain.model.NXR
import com.nx.nxr.ui.Color1001
import com.nx.nxr.ui.Color1002

@Composable
fun BoxList(
    nxrs: List<NXR>,
    onDeleteNotes: (nxrs: List<NXR>, prompt: String) -> Unit,
    onDetailClick: (id: Int) -> Unit,
    query: String = ""
) {

    var previousHeader = ""

    val queriedNotes = remember(key1 = query, key2 = nxrs) {
        if ( query.isEmpty() ) { nxrs }
        else { nxrs.filter { it.text.contains(query) || it.title.contains(query) } }
    }

    LazyColumn {

        itemsIndexed(items = queriedNotes) { index, nxrs ->
            if ( nxrs.getDay() != previousHeader ) {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)) {
                Text(text = nxrs.getDay(), color = Color.Gray, fontSize = 10.sp)
            }
            previousHeader =  nxrs.getDay()
        }

            BoxListItem(
                note = nxrs,
                onDelete = { onDeleteNotes(listOf(nxrs), "Are you want to delete this ?") },
                onDetailClick = { onDetailClick(it) },
                noteBackGround = if (index % 2 == 0) { Color1001 } else Color1002 ) // * day note 2 color


            Divider(
                modifier = Modifier.padding(top = 14.dp, bottom = 12.dp, end = 8.dp),
                color = MaterialTheme.colors.onError,
                thickness = 1.dp
            )
        }
    }
}
