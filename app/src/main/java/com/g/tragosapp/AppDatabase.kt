package com.g.tragosapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.domain.TragosDao

/**
 * Created by Gastón Saillén on 07 July 2020
 */
@Database(entities = arrayOf(DrinkEntity::class),version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tragoDao(): TragosDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"tabla_tragos").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}