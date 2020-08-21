package com.g.tragosapp.application.injection

import com.g.tragosapp.data.CocktailDataSource
import com.g.tragosapp.data.DefaultCocktailDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: DefaultCocktailDataSource): CocktailDataSource
}