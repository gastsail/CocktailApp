package com.g.tragosapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainViewModel @ViewModelInject constructor (private val repo:Repo):ViewModel(){

    private val mutableCocktailName = MutableLiveData<String>()


    fun setCocktail(cocktailName:String){
        mutableCocktailName.value = cocktailName
    }

    init {
        setCocktail("margarita")
    }


    val fetchCocktailList = mutableCocktailName.distinctUntilChanged().switchMap { cocktailName ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                repo.getCocktailList(cocktailName).collect {
                    emit(it)
                }
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }


    fun saveCocktail(cocktail:FavoritesEntity){
        viewModelScope.launch {
            repo.saveCocktail(cocktail)
        }
    }


    fun getFavoriteCocktails() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(repo.getFavoriteCocktails())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteCocktail(cocktail: FavoritesEntity) = liveData(viewModelScope.coroutineContext + Dispatchers.IO)  {
        emit(Resource.Loading())
        try{
            emit(repo.deleteCocktail(cocktail))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}