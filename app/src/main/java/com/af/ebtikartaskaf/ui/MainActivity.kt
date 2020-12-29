package com.af.ebtikartaskaf.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.af.ebtikartaskaf.R
import com.af.ebtikartaskaf.databinding.ActivityMainBinding
import com.af.ebtikartaskaf.ui.fragments.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_person)
                .build()
        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, appBarConfiguration)

    }

    companion object {
        private const val TAG = "MainActivity"
    }

}