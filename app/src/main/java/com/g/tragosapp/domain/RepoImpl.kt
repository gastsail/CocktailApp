package com.g.tragosapp.domain

import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl @Inject constructor(private val dataSource: DataSource): Repo {

    override suspend fun getCocktailList(cocktailName:String): Resource<List<Drink>> {
        return dataSource.getCocktailByName(cocktailName)!!
    }

    override suspend fun getFavoriteCocktails(): Resource<List<Drink>> {
        return dataSource.getFavoritesCocktails()
    }

    override suspend fun insertCocktail(cocktail: DrinkEntity) {
        dataSource.insertCocktailIntoRoom(cocktail)
    }

    override suspend fun deleteCocktail(cocktail: DrinkEntity): Resource<List<Drink>> {
        dataSource.deleteCocktail(cocktail)
        return getFavoriteCocktails()
    }
}