package com.g.tragosapp.data

import com.g.tragosapp.data.model.Cocktail
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.data.model.asCocktailEntity
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 10 August 2020
 */

@ExperimentalCoroutinesApi
class DefaultCocktailDataSource @Inject constructor(
    private val networkDataSource: DataSource,
    private val localDataSource: DataSource
) : DataSource {

    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>?> =
        callbackFlow {

            offer(getCachedCocktails(cocktailName))

            networkDataSource.getCocktailByName(cocktailName).collect {
                when (it) {
                    is Resource.Success -> {
                        for (cocktail in it.data) {
                            saveCocktail(cocktail.asCocktailEntity())
                        }
                        offer(getCachedCocktails(cocktailName))
                    }
                    is Resource.Failure -> {
                        offer(getCachedCocktails(cocktailName))
                    }
                }
            }
            awaitClose { cancel() }
        }

    override suspend fun saveFavoriteCocktail(cocktail: FavoritesEntity) {
        localDataSource.saveFavoriteCocktail(cocktail)
    }

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        localDataSource.saveCocktail(cocktail)
    }

    override suspend fun getFavoritesCocktails(): Resource<List<Cocktail>> {
        return localDataSource.getFavoritesCocktails()
    }

    override suspend fun deleteCocktail(cocktail: FavoritesEntity) {
        localDataSource.deleteCocktail(cocktail)
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>>? {
        return localDataSource.getCachedCocktails(cocktailName)
    }
}