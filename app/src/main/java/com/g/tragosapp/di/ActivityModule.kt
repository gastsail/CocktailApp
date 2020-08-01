package com.g.tragosapp.di

import com.g.tragosapp.data.DataSourceImpl
import com.g.tragosapp.domain.DataSource
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindDataSource(dataSource: DataSourceImpl): DataSource

    @Binds
    abstract fun bindRepo(repo: RepoImpl): Repo

}