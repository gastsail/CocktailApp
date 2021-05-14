package com.g.tragosapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.g.tragosapp.data.model.CocktailEntity
import com.g.tragosapp.data.model.FavoritesEntity
import com.g.tragosapp.getOrAwaitValue
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CocktailDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: CocktailDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries().build()
        dao = database.cocktailDao()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testSaveFavoriteCocktail() = runBlockingTest {

        val favoritesEntity = FavoritesEntity(
            "1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice", "A simple 100% orange juice",
            "Non_Alcoholic"
        )
        dao.saveFavoriteCocktail(favoritesEntity)

        val cockTails = dao.getAllFavoriteDrinksWithChanges().getOrAwaitValue()

        assertThat(cockTails).isNotEmpty()
        assertThat(cockTails).contains(favoritesEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetAllFavoriteCocktail() = runBlockingTest {

        val favoritesEntity = FavoritesEntity(
            "1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice", "A simple 100% orange juice",
            "Non_Alcoholic"
        )
        val favoritesEntity2 = FavoritesEntity(
            "2",
            "https://5.imimg.com/data5/YV/JM/ZE/SELLER-8455061/guava-juice-500x500.png",
            "Guava Juice", "A simple 100% guava juice",
            "Non_Alcoholic"
        )
        dao.saveFavoriteCocktail(favoritesEntity)
        dao.saveFavoriteCocktail(favoritesEntity2)

        val cockTails = dao.getAllFavoriteDrinks()

        assertThat(cockTails).isNotEmpty()
        assertThat(cockTails.size).isEqualTo(2)
        assertThat(cockTails).contains(favoritesEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCocktailById() = runBlockingTest {

        val favoritesEntity = FavoritesEntity(
            "1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice", "A simple 100% orange juice",
            "Non_Alcoholic"
        )
        dao.saveFavoriteCocktail(favoritesEntity)

        val cockTail = dao.getCocktailById(favoritesEntity.cocktailId)

        assertThat(cockTail).isNotNull()
        assertThat(cockTail).isEqualTo(favoritesEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDeleteFavoriteCocktail() = runBlockingTest {
        val favoritesEntity = FavoritesEntity(
            "1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice", "A simple 100% orange juice",
            "Non_Alcoholic"
        )
        dao.saveFavoriteCocktail(favoritesEntity)
        dao.deleteFavoriteCoktail(favoritesEntity)

        val cockTails = dao.getAllFavoriteDrinksWithChanges().getOrAwaitValue()
        assertThat(cockTails).isEmpty()
        assertThat(cockTails).doesNotContain(favoritesEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveCocktail() = runBlockingTest {
        val cocktailEntity = CocktailEntity(
            "1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice", "A simple 100% orange juice",
            "Non_Alcoholic"
        )
        dao.saveCocktail(cocktailEntity)

        val cockTails = dao.getCocktails("Orange Juice")
        assertThat(cockTails).isNotEmpty()
    }


    @After
    fun tearDown() {
        database.close()
    }
}