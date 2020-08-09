package com.g.tragosapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.domain.service.CocktailDao

/**
 * Created by Gastón Saillén on 07 July 2020
 */
@Database(entities = arrayOf(DrinkEntity::class),version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}