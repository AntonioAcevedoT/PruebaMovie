package com.example.pruebamovie.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebamovie.R
import com.example.pruebamovie.databinding.ActivityMainBinding
import com.example.pruebamovie.ui.adapter.AdapterMovies
import com.example.pruebamovie.viewmodels.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:MovieViewModel
    private lateinit var adapter: AdapterMovies


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getMovies()
        setupRecyclerView()

        viewModel.listaPeliculas.observe(this){
           if (it!= null) {
               adapter.list = it
               adapter.notifyDataSetChanged()
           }
        }
        /*navController = Navigation.findNavController(this, R.id.nav_movie)
        NavigationUI.setupActionBarWithNavController(this, navController)*/
    }

    /*   override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }*/

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvPeliculas.layoutManager = layoutManager
        adapter = AdapterMovies(this, arrayListOf())
        binding.rvPeliculas.adapter= adapter
    }

}