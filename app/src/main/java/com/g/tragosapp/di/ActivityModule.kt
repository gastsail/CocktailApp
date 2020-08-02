package com.g.tragosapp.di

import com.g.tragosapp.data.DataSourceImpl
import com.g.tragosapp.domain.DataSource
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Gastón Saillén on 01 August 2020
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl): Repo

    @Binds
    abstract fun bindDatasourceImpl(dataSourceImpl: DataSourceImpl): DataSource

}