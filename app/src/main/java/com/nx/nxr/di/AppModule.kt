package com.nx.nxr.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.nx.nxr.core.Constants
import com.nx.nxr.core.UriPermissionHelper
import com.nx.nxr.data.dao.AppDao
import com.nx.nxr.data.network.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application) : AppDb =
        Room.databaseBuilder(
            app.applicationContext,
            AppDb::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun getDao(appDb: AppDb): AppDao = appDb.AppDao()

    @Provides
    @Singleton
    fun provideUriPermissionHelper(
        @ApplicationContext context: Context
    ) = UriPermissionHelper(context)

}