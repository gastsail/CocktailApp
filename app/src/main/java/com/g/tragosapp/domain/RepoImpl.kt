package com.g.tragosapp.domain

import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }

    override suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return dataSource.getAlcoholicDrinks(alcoholic)
    }
}