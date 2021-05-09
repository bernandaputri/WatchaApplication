package com.putri.watchaapplication.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.MediaEntity

class TvShowViewModel (private val movieRepository: WatchaRepository) : ViewModel() {
    fun getShows(): LiveData<List<MediaEntity>> = movieRepository.getPopularShow()
}