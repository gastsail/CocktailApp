package com.g.tragosapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Gastón Saillén on 03 July 2020
 */

@Parcelize
data class Drink(
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol:String = "Non_Alcoholic"
):Parcelable


data class DrinkList(
    @SerializedName("drinks")
    val drinksList:List<Drink> = listOf()
)