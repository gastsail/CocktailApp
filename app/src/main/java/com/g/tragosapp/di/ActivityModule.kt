package com.g.tragosapp.di

import com.g.tragosapp.data.remote.RemoteDataSourceImpl
import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.DefaultCocktailDataSource
import com.g.tragosapp.data.local.LocalDataSourceImpl
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

/**
 * Created by Gastón Saillén on 01 August 2020
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl): Repo

    @Binds
    abstract fun bindRemoteDatasourceImpl(remoteDataSourceImpl: RemoteDataSourceImpl): DataSource

    @Binds
    abstract fun bindLocalDataSourceImpl(localDataSourceImpl: LocalDataSourceImpl): DataSource
}

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object MyActivityModule {
    @Provides
    fun provideDefaultCocktailDataSource(networkCocktailDataSourceImpl: RemoteDataSourceImpl,localDataSourceImpl: LocalDataSourceImpl) = DefaultCocktailDataSource(networkCocktailDataSourceImpl,localDataSourceImpl)
}