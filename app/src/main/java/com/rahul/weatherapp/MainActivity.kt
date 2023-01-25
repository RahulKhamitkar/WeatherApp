package com.rahul.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.rahul.weatherapp.databinding.ActivityMainBinding
import com.rahul.weatherapp.feature.current_day.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController: NavController =
            Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)

        val bottomNavigationView = binding.bottomNav
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }
}