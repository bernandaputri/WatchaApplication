package com.putri.watchaapplication.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.MediaEntity

class MovieViewModel (private val movieRepository: WatchaRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MediaEntity>> = movieRepository.getPopularMovie()
}