package com.g.tragosapp

import com.g.tragosapp.data.local.AppDatabaseTest
import com.g.tragosapp.data.local.CocktailDaoTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ExampleInstrumentedTest::class,
    AppDatabaseTest::class,
    CocktailDaoTest::class
)
class CocktailAppTestSuit