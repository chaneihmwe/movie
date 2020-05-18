package com.example.retrofitmviedb.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.retrofitmviedb.R
import com.example.retrofitmviedb.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var movieViewModel: MovieViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        movieAdapter = MovieAdapter()
        movieRecyclerView.adapter = movieAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.loadResult()
        movieViewModel.getResult().observe(viewLifecycleOwner, Observer {
            movieRecyclerView.visibility = View.VISIBLE
            txtError.visibility = View.GONE
            movieAdapter.updatedList(it)
        })
        movieViewModel.getError().observe(viewLifecycleOwner, Observer {
            movieRecyclerView.visibility = View.GONE
            txtError.visibility = View.VISIBLE

        })
    }
}
