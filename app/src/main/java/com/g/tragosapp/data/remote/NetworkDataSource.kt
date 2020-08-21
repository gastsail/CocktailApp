package com.g.tragosapp.data.remote

import com.g.tragosapp.core.Resource
import com.g.tragosapp.data.model.Cocktail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getCocktailByName(cocktailName)?.cocktailList ?: listOf()
                )
            )
            awaitClose { close() }
        }
}