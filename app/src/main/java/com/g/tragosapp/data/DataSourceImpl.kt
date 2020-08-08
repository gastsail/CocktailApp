package com.g.tragosapp.data

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.data.model.asDrinkList
import com.g.tragosapp.domain.service.TragosDao
import com.g.tragosapp.domain.service.WebService
import com.g.tragosapp.vo.Resource
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSourceImpl @Inject constructor(
    private val tragosDao: TragosDao,
    private val webService: WebService
) : DataSource {

    override suspend fun getTragoByName(nombreTrago: String): Resource<List<Drink>> {
        return Resource.Success(webService.getTragoByName(nombreTrago)?.drinksList?: listOf())
    }

    override suspend fun insertTragoIntoRoom(trago: DrinkEntity) {
        tragosDao.insertFavorite(trago)
    }

    override suspend fun getTragosFavoritos(): Resource<List<Drink>> {
        return Resource.Success(tragosDao.getAllFavoriteDrinks().asDrinkList())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        tragosDao.deleteDrink(drink)
    }
}