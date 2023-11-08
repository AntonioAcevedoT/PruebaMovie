package com.oxcoding.movies.data.repository

import NetworkState
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pruebamovie.models.MovieDetailsModel
import com.example.pruebamovie.services.MovieService
import com.example.pruebamovie.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsNetworkDataSource (private val apiService : MovieService, private val compositeDisposable: CompositeDisposable) {

    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedMovieDetailsResponse =  MutableLiveData<MovieDetailsModel>()
    val downloadedMovieResponse: LiveData<MovieDetailsModel>
        get() = _downloadedMovieDetailsResponse


    fun getMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getMovieDetails("Bearer "+ Constants.BARE,movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState(Status.FAILED, "Algo salió mal"))
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
                        }
                    )
            )

        }

        catch (e: Exception){
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }

    }

}