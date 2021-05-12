package com.putri.watchaapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.DetailMediaEntity

class DetailViewModel (private val movieRepository: WatchaRepository) : ViewModel() {

    private var mediaId: Int? = 0

    fun selectedMovie(movieId: Int) {
        this.mediaId = movieId
    }

    fun getDetailMovie() : LiveData<DetailMediaEntity> = movieRepository.getDetailMovie(mediaId as Int)

    fun selectedTvShow(showId: Int) {
        this.mediaId = showId
    }

    fun getDetailTvShow() : LiveData<DetailMediaEntity> = movieRepository.getDetailTvShow(mediaId as Int)

}