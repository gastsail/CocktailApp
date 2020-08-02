package com.g.tragosapp.utils.ext

import com.g.tragosapp.data.model.Drink
import com.g.tragosapp.data.model.DrinkEntity

/**
 * Created by Gastón Saillén on 02 August 2020
 */

fun List<DrinkEntity>.asDrinkList(): MutableList<Drink> = this.map {
    Drink(it.tragoId, it.imagen, it.nombre, it.descripcion, it.hasAlcohol)
}.toMutableList()

