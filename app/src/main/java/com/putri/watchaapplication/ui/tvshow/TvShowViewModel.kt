package com.putri.watchaapplication.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.vo.Resource

class TvShowViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {
    fun getShows(): LiveData<Resource<PagedList<ShowEntity>>> = watchaRepository.getPopularShow()
}