package com.g.tragosapp.di

import android.content.Context
import androidx.room.Room
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.domain.service.WebService
import com.g.tragosapp.utils.AppConstants.BASE_URL
import com.g.tragosapp.utils.AppConstants.TABLE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        context,
        AppDatabase::class.java,
        TABLE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideTragosDao(db: AppDatabase) = db.tragoDao()

    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(WebService::class.java)
}