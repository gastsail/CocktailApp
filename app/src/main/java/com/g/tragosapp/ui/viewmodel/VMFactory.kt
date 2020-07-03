package com.g.tragosapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.g.tragosapp.domain.Repo

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class VMFactory(private val repo: Repo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}