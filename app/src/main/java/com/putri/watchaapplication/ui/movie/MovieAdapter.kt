package com.putri.watchaapplication.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.databinding.ItemsListBinding
import com.putri.watchaapplication.ui.detail.DetailActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MediaViewHolder>() {
    private var listMovies = ArrayList<MediaEntity>()

    fun setMovies(movies: List<MediaEntity>) {
        if (movies.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    class MediaViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MediaEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movies.poster_path)
                    .apply(RequestOptions().override(80,120))
                    .transform(RoundedCorners(15))
                    .into(mediaPoster)

                itemTitle.text = movies.title
                itemRating.text = movies.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("id", movies.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val itemsListBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}