package com.g.tragosapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Gastón Saillén on 03 July 2020
 */

@Parcelize
data class Drink(
    val imagen: String = "",
    val nombre: String = "",
    val descripcion: String = ""
):Parcelable