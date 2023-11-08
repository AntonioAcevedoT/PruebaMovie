package com.oxcoding.movies.ui.SingleMovie

import androidx.lifecycle.ViewModel
import com.example.pruebamovie.repository.MovilDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository : MovilDetailsRepository, movieId: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  movieDetails by lazy {
        movieRepository.getMovieDetails(compositeDisposable,movieId)
    }

    val networkState by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}