package com.g.tragosapp.data

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.data.model.asDrinkList
import com.g.tragosapp.domain.service.CocktailDao
import com.g.tragosapp.domain.service.WebService
import com.g.tragosapp.vo.Resource
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSourceImpl @Inject constructor(
    private val cocktailDao: CocktailDao,
    private val webService: WebService
) : DataSource {

    override suspend fun getCocktailByName(nombreTrago: String): Resource<List<Drink>> {
        return Resource.Success(webService.getCocktailByName(nombreTrago)?.drinksList?: listOf())
    }

    override suspend fun insertCocktailIntoRoom(trago: DrinkEntity) {
        cocktailDao.insertFavorite(trago)
    }

    override suspend fun getFavoritesCocktails(): Resource<List<Drink>> {
        return Resource.Success(cocktailDao.getAllFavoriteDrinks().asDrinkList())
    }

    override suspend fun deleteCocktail(drink: DrinkEntity) {
        cocktailDao.deleteCoktail(drink)
    }
}