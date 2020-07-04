package com.g.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainViewModel(private val repo:Repo):ViewModel(){

    private val tragoNameData = MutableLiveData<String>()
    private val alcoholicOrnotData = MutableLiveData<String>()

    fun setTrago(tragoName:String){
        tragoNameData.value = tragoName
    }

    fun setAlcoholicOrNotFilter(alcoholicOrNot:String){
        alcoholicOrnotData.value = alcoholicOrNot
    }

    init {
        setTrago("mojito")
    }

    val fetchTragosList = tragoNameData.distinctUntilChanged().switchMap { tragoName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getTragosList(tragoName))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchAlcoholicFilter = alcoholicOrnotData.distinctUntilChanged().switchMap { isAlcoholic ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getAlcoholicDrinks(isAlcoholic))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}