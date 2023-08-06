package com.nx.nxr.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nx.nxr.data.dao.AppDao
import com.nx.nxr.domain.model.NXR

@Database(entities = [ NXR::class], version = 1)
abstract class AppDb : RoomDatabase() {  abstract fun AppDao(): AppDao }
