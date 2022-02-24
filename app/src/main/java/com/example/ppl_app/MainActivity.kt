package com.example.ppl_app

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Configure navigation bar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar);
        toolbar.setTitle("PPL Workouts");
        setSupportActionBar(toolbar)

        // setup the bottom navigation view
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        // setup app bar configuration
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragmentWorkouts,R.id.fragmentInsights,R.id.fragmentSettings))
        setupActionBarWithNavController(navController,appBarConfiguration)

        // setup the tab layout and the view pager
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.fragmentAddWorkout) {
                bottomNavigationView.visibility = View.GONE
                toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }



    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }




}