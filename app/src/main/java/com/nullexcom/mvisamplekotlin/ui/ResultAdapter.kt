package com.nullexcom.mvisamplekotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import com.nullexcom.mvisamplekotlin.R
import com.nullexcom.mvisamplekotlin.models.Movie
import com.nullexcom.mvisamplekotlin.ui.ResultAdapter.ResultViewHolder
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.item_result.view.*

class ResultAdapter internal constructor(private val context: Context) :
    RecyclerView.Adapter<ResultViewHolder>() {
    private val movies: MutableList<Movie> = mutableListOf()
    private val onItemClick: Subject<Movie> = PublishSubject.create()

    fun itemClicks(): Subject<Movie> {
        return onItemClick
    }

    fun refresh(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun append(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ResultViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(i: Int) {
            with(itemView) {
                val movie = movies[i]
                Glide.with(context).load(movie.imgUrl).into(imgThumbnail)
                tvEntryTitle.setText(movie.entryTitle)
                tvOriginalTitle.setText(movie.originalTitle)
                itemView.clicks().doOnNext { onItemClick.onNext(movie) }.subscribe()
            }
        }
    }

}