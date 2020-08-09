package com.g.tragosapp.domain.service

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkList
import com.g.tragosapp.vo.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Gastón Saillén on 04 July 2020
 */
interface WebService {

    @GET("search.php")
    suspend fun getCocktailByName(@Query(value = "s") tragoName:String): DrinkList?
}