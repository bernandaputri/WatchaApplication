package com.putri.watchaapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.DetailMediaEntity

class DetailViewModel (private val movieRepository: WatchaRepository) : ViewModel() {

    private var mediaId: Int? = 0

    fun selectedMovie(mediaId: Int) {
        this.mediaId = mediaId
    }

    fun getDetailMovie() : LiveData<DetailMediaEntity> = mediaId?.let { movieRepository.getDetailMovie(it) } as LiveData<DetailMediaEntity>

    fun selectedTvShow(mediaId: Int) {
        this.mediaId = mediaId
    }

    fun getDetailTvShow() : LiveData<DetailMediaEntity> = mediaId?.let { movieRepository.getDetailTvShow(it) } as LiveData<DetailMediaEntity>

}