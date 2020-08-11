package com.g.tragosapp.data.remote

import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.domain.remote.WebService
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */
@ExperimentalCoroutinesApi
class RemoteDataSourceImpl @Inject constructor(
    private val webService: WebService
) : DataSource {

    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>> = callbackFlow {
        offer(Resource.Success(webService.getCocktailByName(cocktailName)?.cocktailList?: listOf()))
        awaitClose { close() }
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>>? {
        TODO("not implemented")
    }

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        TODO("not implemented")
    }

    override suspend fun saveFavoriteCocktail(cocktail: FavoritesEntity) {
        TODO("not implemented")
    }

    override suspend fun getFavoritesCocktails(): Resource<List<Cocktail>> {
        TODO("not implemented")
    }

    override suspend fun deleteCocktail(cocktail: FavoritesEntity) {
        TODO("not implemented")
    }
}