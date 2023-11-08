package com.example.pruebamovie.repository

import NetworkState
import androidx.lifecycle.LiveData
import com.example.pruebamovie.models.MovieDetailsModel
import com.example.pruebamovie.services.MovieService
import com.oxcoding.movies.data.repository.MovieDetailsNetworkDataSource
import io.reactivex.disposables.CompositeDisposable

class MovilDetailsRepository(private val apiService : MovieService)  {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

     fun getMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetailsModel> {
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.getMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}