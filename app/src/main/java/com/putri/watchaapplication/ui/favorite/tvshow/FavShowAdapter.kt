package com.putri.watchaapplication.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.databinding.ItemsListBinding
import com.putri.watchaapplication.ui.detail.DetailActivity

class FavShowAdapter : PagedListAdapter<ShowEntity, FavShowAdapter.FavTvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShowEntity>() {
            override fun areItemsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean {
                return oldItem.showId == newItem.showId
            }
            override fun areContentsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): ShowEntity? = getItem(swipedPosition)

    class FavTvShowViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shows: ShowEntity) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500" + shows.showPoster)
                        .apply(RequestOptions().override(80,120))
                        .transform(RoundedCorners(15))
                        .into(mediaPoster)

                itemTitle.text = shows.showTitle
                itemRating.text = shows.showRating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("id", shows.showId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "tvShow")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvShowViewHolder {
        val itemsListBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTvShowViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: FavTvShowViewHolder, position: Int) {
        val show = getItem(position)
        if (show != null) {
            holder.bind(show)
        }
    }

}