package com.putri.watchaapplication.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.databinding.ItemsListBinding
import com.putri.watchaapplication.ui.detail.DetailActivity

class FavMovieAdapter : PagedListAdapter<MovieEntity, FavMovieAdapter.FavMovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    class FavMovieViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500" + movies.moviePoster)
                        .apply(RequestOptions().override(80,120))
                        .transform(RoundedCorners(15))
                        .into(mediaPoster)

                itemTitle.text = movies.movieTitle
                itemRating.text = movies.movieRating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("id", movies.movieId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val itemsListBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}