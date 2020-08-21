package com.g.tragosapp.data

import androidx.lifecycle.LiveData
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Gastón Saillén on 16 July 2020
 */
interface CocktailDataSource {
    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>>

    suspend fun saveFavoriteCocktail(cocktail: Cocktail)

    suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean

    suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>>

    suspend fun saveCocktail(cocktail: CocktailEntity)

    fun getFavoritesCocktails(): LiveData<List<Cocktail>>

    suspend fun deleteFavoriteCocktail(cocktail: Cocktail)
}