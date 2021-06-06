package com.putri.watchaapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.vo.Resource

interface WatchaDataSource {
    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)

    fun getFavMovie(): LiveData<PagedList<MovieEntity>>

    fun getPopularShow(): LiveData<Resource<PagedList<ShowEntity>>>

    fun getDetailShow(tvShowId: Int): LiveData<Resource<ShowEntity>>

    fun setFavShow(tvShow: ShowEntity, state: Boolean)

    fun getFavShow(): LiveData<PagedList<ShowEntity>>
}