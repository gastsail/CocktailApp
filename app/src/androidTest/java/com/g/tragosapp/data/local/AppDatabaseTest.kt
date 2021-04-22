package com.g.tragosapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.g.tragosapp.data.model.CocktailEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isDatabaseNotOpen() {
        assertThat(database.isOpen).isFalse()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isDatabaseOpen() = runBlockingTest {
        val cocktailEntity = CocktailEntity("1",
            "https://img1.mashed.com/img/gallery/heres-what-happens-when-you-drink-orange-juice-every-day/intro-1587655828.jpg",
            "Orange Juice","A simple 100% orange juice",
            "Non_Alcoholic")
        database.cocktailDao().saveCocktail(cocktailEntity)
        assertThat(database.isOpen).isTrue()
    }

    @After
    fun tearDown() {
        database.close()
    }

}