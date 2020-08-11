package com.g.tragosapp.data

import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 16 July 2020
 */
interface DataSource {
    suspend fun getCocktailByName(cocktailName: String): Resource<List<Cocktail>>?
    suspend fun saveFavoriteCocktail(cocktail: FavoritesEntity)
    suspend fun saveCocktail(cocktail: CocktailEntity)
    suspend fun getCocktails(cocktailName: String):Resource<List<Cocktail>>?
    suspend fun getFavoritesCocktails(): Resource<List<Cocktail>>
    suspend fun deleteCocktail(cocktail: FavoritesEntity)
}