package com.g.tragosapp.domain

import com.g.tragosapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gastón Saillén on 04 July 2020
 */
interface WebService {

    @GET("search.php")
    suspend fun getTragoByName(@Query("s") tragoName:String): DrinkList

    @GET("filter.php")
    suspend fun getAlcoholicDrink(@Query("a") alcoholicOrNot:String): DrinkList
}