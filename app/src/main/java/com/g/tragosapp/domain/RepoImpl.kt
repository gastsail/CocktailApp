package com.g.tragosapp.domain

import com.g.tragosapp.data.DataSource
import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.vo.Resource

/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl(private val dataSource: DataSource): Repo {

    override fun getTragosList(): Resource<List<Drink>> {
        return dataSource.generateTragosList
    }
}