package com.g.tragosapp.data.remote

import com.g.tragosapp.data.model.CocktailList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gastón Saillén on 04 July 2020
 */
interface WebService {
    @GET("search.php")
    suspend fun getCocktailByName(@Query(value = "s") tragoName: String): CocktailList?
}