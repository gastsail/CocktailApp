package com.g.tragosapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.g.tragosapp.domain.Repo
import com.g.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainViewModel(private val repo:Repo):ViewModel(){

    val fetchTragosList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(repo.getTragosList("margarita"))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}