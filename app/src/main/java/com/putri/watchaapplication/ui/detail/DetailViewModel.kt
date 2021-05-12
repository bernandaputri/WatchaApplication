package com.putri.watchaapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.DetailMediaEntity

class DetailViewModel (private val movieRepository: WatchaRepository) : ViewModel() {

    private var movieId: Int? = 0

    fun selectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getDetailMovie() : LiveData<DetailMediaEntity> = movieRepository.getDetailMovie(movieId as Int)

    private var showId: Int? = 0

    fun selectedTvShow(showId: Int) {
        this.showId = showId
    }

    fun getDetailTvShow() : LiveData<DetailMediaEntity> = movieRepository.getDetailTvShow(showId as Int)

}