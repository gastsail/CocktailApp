package com.g.tragosapp.di

import android.content.Context
import androidx.room.Room
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Gastón Saillén on 31 July 2020
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "tabla_tragos")
            .build()

    @Singleton
    @Provides
    fun provideTragosDao(db: AppDatabase) = db.tragoDao()

}