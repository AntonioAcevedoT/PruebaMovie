package com.example.pruebamovie.services

import com.example.pruebamovie.models.MovieDetailsModel
import com.example.pruebamovie.models.PeliculasResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET("movie/now_playing")
    @Headers("Accept: application/json")
    suspend fun getAllMovies(@Header("Authorization") authHeader:String): Response<PeliculasResponse>

    @GET("popular")
    @Headers("Accept: application/json")
    suspend fun getPopularDetails(@Header("Authorization") authHeader:String):Response<PeliculasResponse>

    @GET("movie/{movie_id}")
    @Headers("Accept: application/json")
   fun getMovieDetails(@Header("Authorization") authHeader:String,@Path("movie_id") id: Int): Single<MovieDetailsModel>

}