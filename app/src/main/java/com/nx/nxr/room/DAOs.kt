package com.nx.nxr.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOs {

    @Query("SELECT * FROM NXRtable WHERE NXRtable.id=:id")  // **  อาจจเผิดตนง WHERE
    suspend fun getNxrById(id: Int) : ENTITYs?

    @Query("SELECT * FROM NXRtable ORDER BY dateUpdated DESC")
    fun getNxrs() : LiveData<List<ENTITYs>>

    @Delete
    fun deleteNxr(nxr: ENTITYs) : Int

    @Update
    fun updateNxr(nxr: ENTITYs) : Int

    @Insert
    fun insertNxr(note: ENTITYs)

}