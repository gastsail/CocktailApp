package com.g.tragosapp.domain

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 16 July 2020
 */
interface DataSource {
    suspend fun getTragoByName(nombreTrago: String): Resource<List<Drink>>
    suspend fun insertTragoIntoRoom(trago: DrinkEntity)
    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>>
    suspend fun deleteDrink(drink: DrinkEntity)
}