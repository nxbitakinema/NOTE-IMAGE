package com.nx.nxr.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ ENTITYs::class], version = 1)
abstract class DATABASEs : RoomDatabase() {  abstract fun DAOs(): DAOs }
