package com.g.tragosapp.domain

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
interface Repo {
    suspend fun getTragosList(nombreTrago:String): Resource<List<Drink>>?
    suspend fun getTragosFavoritos(): Resource<List<Drink>>
    suspend fun insertTrago(trago:DrinkEntity)
    suspend fun deleteDrink(drink: DrinkEntity):Resource<List<Drink>>
}