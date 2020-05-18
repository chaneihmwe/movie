package com.example.retrofitmviedb.Api

import com.example.retrofitmviedb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("top_rated")
    fun getMovie( @Query("api_key") apiKey:String):Call<Movie>
}