package com.putri.watchaapplication.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.vo.Resource

class MovieViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = watchaRepository.getPopularMovie()
}