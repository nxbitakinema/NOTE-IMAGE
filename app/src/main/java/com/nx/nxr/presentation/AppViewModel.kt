package com.nx.nxr.presentation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nx.nxr.core.UriPermissionHelper
import com.nx.nxr.data.dao.AppDao
import com.nx.nxr.domain.model.NXR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel  @Inject constructor(
    private val db: AppDao,
    private val uriPermissionHelper: UriPermissionHelper
) : ViewModel() {

    val nxs: LiveData<List<NXR>> = db.getDAOs()

    var openDialog by mutableStateOf(false)

    suspend fun getVIEWMODELs(nxrId: Int): NXR? {
        return db.getDAOsById(nxrId)
    }

    fun delete(nxr: NXR) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteDAOs(nxr)
        }
    }

    fun update(nxr: NXR) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateDAOs(nxr)
        }
    }

    fun create(
        title: String,
        text: String,
        imageUri: String?
    ) {
        val ccc = NXR(
            title = title,
            text = text,
            imageUri = imageUri
        )
        viewModelScope.launch(Dispatchers.IO) {
            db.insertDAOs(ccc)
        }
    }

    fun requestUriPermission(uri: Uri) {
        viewModelScope.launch {
            uriPermissionHelper.get(uri)
        }
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

}

