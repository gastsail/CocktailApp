package com.g.tragosapp.data

import com.g.tragosapp.AppDatabase
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.domain.DataSource
import com.g.tragosapp.vo.Resource
import com.g.tragosapp.vo.RetrofitClient

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSourceImpl(private val appDatabase: AppDatabase): DataSource{

   override suspend fun getTragoByName(nombreTrago:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(nombreTrago).drinksList)
    }

    override suspend fun insertTragoIntoRoom(trago:DrinkEntity){
        appDatabase.tragoDao().insertFavorite(trago)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.tragoDao().getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        appDatabase.tragoDao().deleteDrink(drink)
    }
}