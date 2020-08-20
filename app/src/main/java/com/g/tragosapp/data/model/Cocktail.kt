package com.g.tragosapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Gastón Saillén on 03 July 2020
 */

@Parcelize
data class Cocktail(
    @SerializedName("idDrink")
    val cocktailId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
) : Parcelable

data class CocktailList(
    @SerializedName("drinks")
    val cocktailList: List<Cocktail> = listOf()
)

@Entity(tableName = "cocktailTable")
data class CocktailEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name = "trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)

@Entity(tableName = "favoritesTable")
data class FavoritesEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name = "trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)

fun List<FavoritesEntity>.asDrinkList(): List<Cocktail> = this.map {
    Cocktail(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
}

fun List<CocktailEntity>.asCocktailList(): List<Cocktail> = this.map {
    Cocktail(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
}

fun Cocktail.asCocktailEntity(): CocktailEntity =
    CocktailEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)

fun Cocktail.asFavoriteEntity(): FavoritesEntity =
    FavoritesEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)