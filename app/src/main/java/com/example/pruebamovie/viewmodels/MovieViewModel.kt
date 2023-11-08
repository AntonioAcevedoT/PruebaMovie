package com.example.pruebamovie.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamovie.core.hilt.HiltModules
import com.example.pruebamovie.models.Result
import com.example.pruebamovie.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MovieViewModel:ViewModel() {

    private val listMovies = MutableLiveData<List<Result>>()
    val listaPeliculas:LiveData<List<Result>> = listMovies

    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = HiltModules.provideRetrofitInterface().getAllMovies("Bearer "+Constants.BARE)
            withContext(Dispatchers.Main){
                listMovies.value = response.body()?.results?.sortedBy { it.voteCount }
            }
        }
    }

    fun getMoviesPopulares(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = HiltModules.provideRetrofitInterface().getPopularDetails(Constants.API_KEY)
            withContext(Dispatchers.Main){
                listMovies.value = response.body()?.results?.sortedBy { it.voteCount }
            }
        }
    }
}