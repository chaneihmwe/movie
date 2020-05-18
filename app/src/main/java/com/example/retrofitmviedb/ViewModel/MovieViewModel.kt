package com.example.retrofitmviedb.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmviedb.Api.MovieApi
import com.example.retrofitmviedb.model.Movie
import com.example.retrofitmviedb.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var result: MutableLiveData<List<Result>> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult(): LiveData<List<Result>> = result

    fun getError() : LiveData<Boolean> = error

    private val movieApi: MovieApi = MovieApi()

    fun loadResult() {
        val apiCall = movieApi.getMovie("e180bf7bddd48a41f6dc6afd38888c0b")

        apiCall.enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                loading.value = false
                error.value = true
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                loading.value = false
                var response = response.body()?.results ?: emptyList()
                result.value = response
            }

        })

    }
}