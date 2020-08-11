package com.g.tragosapp.domain.local

import androidx.room.*
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity

/**
 * Created by Gastón Saillén on 07 July 2020
 */

@Dao
interface CocktailDao {

    @Query("SELECT * FROM favoritesTable")
    suspend fun getAllFavoriteDrinks(): List<FavoritesEntity>

    @Query("SELECT * FROM cocktailTable WHERE trago_nombre LIKE '%' || :cocktailName || '%'") // This Like operator is needed due that the API returns blank spaces in the name
    suspend fun getCocktails(cocktailName: String): List<CocktailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteCocktail(trago: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCocktail(cocktail: CocktailEntity)

    @Delete
    suspend fun deleteFavoriteCoktail(favorites: FavoritesEntity)
}