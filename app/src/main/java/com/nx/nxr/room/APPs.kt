package com.nx.nxr.room

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.room.Room

class APPs : Application(){

    private var db : DATABASEs? = null

    init { instance = this }

    private fun getDb(): DATABASEs {
        return if (db != null){
            db!!
        } else {
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                DATABASEs::class.java, CONSTANTs.DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
            db!!
        }
    }


    companion object {
        private var instance: APPs? = null

        fun getDao(): DAOs {
            return instance!!.getDb().DAOs()
        }

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

    }


}