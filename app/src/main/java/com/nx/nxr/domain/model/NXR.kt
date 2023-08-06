package com.nx.nxr.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nx.nxr.core.Constants.TABLE_NAME
import com.nx.nxr.core.getDateCreated

@Entity(
    tableName = TABLE_NAME,
    indices = [ Index ( value = ["id"] , unique = true ) ]
)

data class NXR(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "textENTITYs")
    val text: String,

    @ColumnInfo(name = "titleENTITYs")
    val title: String,

    @ColumnInfo(name = "dateUpdatedENTITYs")
    val dateUpdated: String = getDateCreated(),

    @ColumnInfo(name = "imageUriENTITYs")
    val imageUri: String? = null

)



