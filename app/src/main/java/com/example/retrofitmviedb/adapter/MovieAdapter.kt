package com.example.retrofitmviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmviedb.R
import com.example.retrofitmviedb.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MovieAdapter (var movieList: List<Result> = ArrayList()):
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindMovie(result: Result){
            itemView.txt_movName.text = result.title
            Picasso.get().load("https://image.tmdb.org/t/p/w200${result.poster_path}").into(itemView.mov_img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return  MovieViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieList[position])
    }

    fun updatedList(result: List<Result>) {
        this.movieList = result
        notifyDataSetChanged()
    }
}