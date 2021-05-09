package com.putri.watchaapplication.ui.tvshow

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

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MediaViewHolder>() {
    private var listShows = ArrayList<MediaEntity>()

    fun setShows(shows: List<MediaEntity>) {
        if (shows.isNullOrEmpty()) return
        this.listShows.clear()
        this.listShows.addAll(shows)
    }

    class MediaViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shows: MediaEntity) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500" + shows.poster_path)
                    .apply(RequestOptions().override(80,120))
                    .transform(RoundedCorners(15))
                    .into(mediaPoster)

                itemTitle.text = shows.title
                itemRating.text = shows.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("id", shows.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "tvShow")
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
        val movie = listShows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listShows.size
}