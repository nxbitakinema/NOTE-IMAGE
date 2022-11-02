package com.nx.nxr.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VIEWMODELs ( private val db: DAOs) : ViewModel() {

    val nxrs: LiveData<List<ENTITYs>> = db.getNxrs()


    fun deleteNxr(nxr: ENTITYs) {
        viewModelScope.launch(Dispatchers.IO){
            db.deleteNxr(nxr)
        }
    }

    fun updateNxr(nxr: ENTITYs) {
        viewModelScope.launch(Dispatchers.IO){
            db.updateNxr(nxr)
        }
    }

    fun createNxr(title: String, nxr: String, image: String? = null) {
        viewModelScope.launch(Dispatchers.IO){
            db.insertNxr(ENTITYs(title = title, nxr = nxr, imageUri = image))
        }
    }

    suspend fun getNxr(nxrId : Int) : ENTITYs? {
        return db.getNxrById(nxrId)
    }

}

class ViewModelFactory(
    private val db: DAOs,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  VIEWMODELs(
            db = db,
        ) as T
    }

}