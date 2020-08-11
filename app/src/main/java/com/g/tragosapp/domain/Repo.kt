package com.g.tragosapp.domain

import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
interface Repo {
    suspend fun getCocktailList(cocktailName:String): Resource<List<Cocktail>>?
    suspend fun getFavoriteCocktails(): Resource<List<Cocktail>>
    suspend fun getCachedCocktails(cocktailName: String):Resource<List<Cocktail>>
    suspend fun saveCocktail(cocktail:FavoritesEntity)
    suspend fun deleteCocktail(cocktail: FavoritesEntity):Resource<List<Cocktail>>
}