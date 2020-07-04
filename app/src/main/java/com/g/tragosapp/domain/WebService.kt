package com.g.tragosapp.domain

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

    @GET("search.php?s=")
    suspend fun getTragoByName(@Query(value = "tragoName") tragoName:String): DrinkList
}