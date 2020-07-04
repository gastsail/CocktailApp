package com.g.tragosapp.data

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.vo.Resource
import com.g.tragosapp.vo.RetrofitClient

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSource{

   suspend fun getTragoByName(nombreTrago:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(nombreTrago).drinksList)
    }

    val generateTragosList = Resource.Success(listOf(
        Drink("https://cdn5.recetasdeescandalo.com/wp-content/uploads/2018/09/Coctel-margarita-como-prepararlo.-Receta-e-ingredientes.jpg","Margarita","Con azucar, vodka y nueces"),
        Drink("https://www.recetas-argentinas.com/base/stock/Recipe/2-image/2-image_web.jpg","Fernet","Fernet con coca y 2 hielos"),
        Drink("https://pbs.twimg.com/media/CERSHJwXIAATqjl.jpg", "Toro", "Toro con pritty"),
        Drink("https://66.media.tumblr.com/2a00c67fe2becf9e6704245c2432e625/tumblr_ny82d7tHAT1u916tro1_640.jpg", "Gancia", "Gancia con sprite")
    ))
}