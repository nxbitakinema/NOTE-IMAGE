package com.nx.nxr.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = CONSTANTs.TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
data class ENTITYs (
    @PrimaryKey(autoGenerate = true)    val id: Int? = null,
    @ColumnInfo(name = "nxr")          val nxr: String,
    @ColumnInfo(name = "title")         val title: String,
    @ColumnInfo(name = "dateUpdated")   val dateUpdated: String = getDateCreated(),
    @ColumnInfo(name = "imageUri")     val imageUri: String? = null
)