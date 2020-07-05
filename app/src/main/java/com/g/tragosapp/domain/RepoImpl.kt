package com.g.tragosapp.domain

import android.content.Context
import com.g.tragosapp.AppDatabase
import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl(private val dataSource: DataSource,private val appDatabase: AppDatabase): Repo {

    override suspend fun getTragosList(nombreTrago:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(nombreTrago)
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        appDatabase.tragoDao().insertTrago(trago)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.tragoDao().getAllDrinks())
    }
}