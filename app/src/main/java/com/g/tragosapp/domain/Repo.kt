package com.g.tragosapp.domain

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
interface Repo {
    suspend fun getCocktailList(cocktailName:String): Resource<List<Drink>>?
    suspend fun getFavoriteCocktails(): Resource<List<Drink>>
    suspend fun insertCocktail(cocktail:DrinkEntity)
    suspend fun deleteCocktail(cocktail: DrinkEntity):Resource<List<Drink>>
}