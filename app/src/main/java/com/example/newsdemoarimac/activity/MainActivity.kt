package com.example.newsdemoarimac.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.newsdemoarimac.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragment)
       // setupActionBarWithNavController(navController)

    }


    //For Back Action
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}