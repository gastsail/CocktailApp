package com.g.tragosapp.domain

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl @Inject constructor(private val dataSource: DataSource): Repo {

    override suspend fun getTragosList(nombreTrago:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(nombreTrago)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.insertTragoIntoRoom(trago)
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        dataSource.deleteDrink(drink)
    }
}