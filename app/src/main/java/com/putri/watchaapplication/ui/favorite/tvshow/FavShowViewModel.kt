package com.putri.watchaapplication.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.ShowEntity

class FavShowViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {

    fun getFavShow() : LiveData<PagedList<ShowEntity>> = watchaRepository.getFavShow()

    fun setFavShow(show: ShowEntity) {
        val newState = !show.showAdd
        watchaRepository.setFavShow(show, newState)
    }
}