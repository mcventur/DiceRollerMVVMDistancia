package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ActivityMainBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)

        binding.topAppBar.setOnMenuItemClickListener {
            onItemClicked(it)
        }
    }

    //Evento on click sobre opciones del menú de la barra superior
    private fun onItemClicked(item: MenuItem): Boolean {
        //Si el id del item del menú coincide con el id de un destino de NavGraph, navega a él
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}



