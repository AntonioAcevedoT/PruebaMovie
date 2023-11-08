package com.example.pruebamovie.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.bumptech.glide.Glide
import com.example.pruebamovie.core.hilt.HiltModules
import com.example.pruebamovie.core.hilt.TheMovieDBClient
import com.example.pruebamovie.databinding.ActivityDetailsAcvtivityBinding
import com.example.pruebamovie.models.MovieDetailsModel
import com.example.pruebamovie.repository.MovilDetailsRepository
import com.example.pruebamovie.services.MovieService
import com.example.pruebamovie.utils.ConnectivityInterceptor
import com.example.pruebamovie.utils.Constants
import com.oxcoding.movies.ui.SingleMovie.SingleMovieViewModel
import java.text.NumberFormat
import java.util.*

class DetailsAcvtivity : AppCompatActivity(),OnClickListener {

    private lateinit var binding:ActivityDetailsAcvtivityBinding
    private lateinit var movieRepo:MovilDetailsRepository
    private lateinit var viewModel: SingleMovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsAcvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService : MovieService = TheMovieDBClient.getClient(ConnectivityInterceptor(this))
        movieRepo = MovilDetailsRepository(apiService)
        val movieId: Int = intent.getIntExtra("id",1)
        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
            Toast.makeText(this, "llego bien", Toast.LENGTH_LONG).show()
        })

    }

    override fun onClick(item: View?) {
        Toast.makeText(this, item?.id.toString(), Toast.LENGTH_SHORT).show()
    }
    fun bindUI( it: MovieDetailsModel){
        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieTitle.text = it.title
        binding.movieTagline.text = it.tagline
        binding.movieReleaseDate.text = it.releaseDate
        //movie_rating.text = it.rating
        binding.movieRuntime.text = it.runtime.toString() + " minutes"
        binding.movieOverview.text = it.overview
        val moviePosterURL = Constants.BASE_URL_IMAGE + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(binding.ivMoviePoster);
    }
    private fun getViewModel(movieId:Int): SingleMovieViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepo,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}