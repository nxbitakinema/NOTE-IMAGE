package com.nx.nxr.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nx.nxr.domain.model.NXR

@Dao
interface AppDao {

    @Query("SELECT * FROM NXRtable WHERE nxrtable.id=:id")
    suspend fun getDAOsById(id: Int): NXR?

    @Query("SELECT * FROM NXRtable ORDER BY dateUpdatedENTITYs DESC")
    fun getDAOs(): LiveData<List<NXR>>

    @Delete
    fun deleteDAOs(nxr: NXR): Int

    @Update
    fun updateDAOs(nxr: NXR): Int

    @Insert
    fun insertDAOs(note: NXR)

}