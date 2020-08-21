package com.g.tragosapp.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.g.tragosapp.core.Resource
import com.g.tragosapp.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 10 August 2020
 */

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val cocktailDao: CocktailDao) {
    suspend fun saveFavoriteCocktail(cocktail: Cocktail) {
        return cocktailDao.saveFavoriteCocktail(cocktail.asFavoriteEntity())
    }

    fun getFavoritesCocktails(): LiveData<List<Cocktail>> {
        return cocktailDao.getAllFavoriteDrinksWithChanges().map { it.asDrinkList() }
    }

    suspend fun deleteCocktail(cocktail: Cocktail) {
        return cocktailDao.deleteFavoriteCoktail(cocktail.asFavoriteEntity())
    }

    suspend fun saveCocktail(cocktail: CocktailEntity) {
        cocktailDao.saveCocktail(cocktail)
    }

    suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>> {
        return Resource.Success(cocktailDao.getCocktails(cocktailName).asCocktailList())
    }

    suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean {
        return cocktailDao.getCocktailById(cocktail.cocktailId) != null
    }
}