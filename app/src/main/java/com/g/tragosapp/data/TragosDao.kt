package com.g.tragosapp.data

import androidx.room.*
import com.g.tragosapp.data.model.DrinkEntity

/**
 * Created by Gastón Saillén on 05 July 2020
 */

@Dao
interface TragosDao {

    @Query("SELECT * FROM drinkentity")
    suspend fun getAllDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrago(trago:DrinkEntity)

    @Delete
    suspend fun delete(drink: DrinkEntity)

}