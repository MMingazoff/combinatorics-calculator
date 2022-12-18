package com.itis.combinatoricscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
                .navController
        findViewById<BottomNavigationView>(R.id.bnv_main)
            .setupWithNavController(controller)
    }

    companion object {
        const val savedResult = "SAVED_RES"
    }
}