package com.g.tragosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var navController:NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupActionBar()
  }

  private fun setupActionBar(){
    navController = findNavController(R.id.nav_host_fragment)
    NavigationUI.setupActionBarWithNavController(this,navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp()
  }

}