package com.nx.nxr.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.nx.nxr.domain.model.NXR
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UriPermissionHelper(private val appContext: Context) {
    fun get(uri: Uri) {
        appContext.contentResolver
            .takePersistableUriPermission(uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
}

val FakePlaceHolder = NXR(
    text = "",
    id = 0,
    title = ""
)

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun NXR.getDay(): String {

    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    if (this.dateUpdated.isEmpty()) return ""

    return LocalDateTime
        .parse(this.dateUpdated, format)
        .toLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

}