package com.g.tragosapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity
import com.g.tragosapp.data.model.asDrinkList
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainViewModel @ViewModelInject constructor (private val repo:Repo):ViewModel(){

    private val tragosData = MutableLiveData<String>()


    fun setTrago(tragoName:String){
        tragosData.value = tragoName
    }

    init {
        setTrago("margarita")
    }


    val fetchTragosList = tragosData.distinctUntilChanged().switchMap { nombreTrago ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getTragosList(nombreTrago))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }


    fun guardarTrago(trago:DrinkEntity){
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }


    fun getTragosFavoritos() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(repo.getTragosFavoritos())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }
}