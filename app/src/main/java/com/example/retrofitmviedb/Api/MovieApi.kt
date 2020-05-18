package com.example.retrofitmviedb.Api

import com.example.retrofitmviedb.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieApiInterface: MovieApiInterface

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        movieApiInterface = retrofit.create(MovieApiInterface::class.java)
    }

    fun getMovie(apikey: String):Call<Movie>{
        return movieApiInterface.getMovie(apikey)
    }

}