package com.g.tragosapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.domain.local.CocktailDao

/**
 * Created by Gastón Saillén on 07 July 2020
 */
@Database(entities = [FavoritesEntity::class, CocktailEntity::class],version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}