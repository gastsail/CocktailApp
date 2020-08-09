package com.g.tragosapp.data

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 16 July 2020
 */
interface DataSource {
    suspend fun getCocktailByName(nombreTrago: String): Resource<List<Drink>>?
    suspend fun insertCocktailIntoRoom(trago: DrinkEntity)
    suspend fun getFavoritesCocktails(): Resource<List<Drink>>
    suspend fun deleteCocktail(drink: DrinkEntity)
}