package com.g.tragosapp.domain

import androidx.room.*
import com.g.tragosapp.data.model.DrinkEntity

/**
 * Created by Gastón Saillén on 07 July 2020
 */

@Dao
interface TragosDao {

    @Query("SELECT * FROM tragosEntity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)
}