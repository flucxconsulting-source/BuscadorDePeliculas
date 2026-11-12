package com.formacion.buscadordepeliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formacion.buscadordepeliculas.api.MovieItem
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var movies: List<MovieItem>,
    private val onItemClick: (MovieItem) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        val tvTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val tvYear: TextView = itemView.findViewById(R.id.tvMovieYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.title
        holder.tvYear.text = movie.year

        if ((movie.poster.isNotEmpty()) && (movie.poster != "N/A")) {
            Picasso.get()
                .load(movie.poster)
                .into(holder.ivPoster)
        } else {
            holder.ivPoster.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<MovieItem>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
