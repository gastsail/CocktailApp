package com.g.tragosapp.data

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.vo.Resource
import com.g.tragosapp.vo.RetrofitClient

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSource{

    suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getAlcoholicDrink(alcoholic!!).drinkList)
    }
}