package com.putri.watchaapplication.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.data.local.room.WatchaDao

class LocalDataSource private constructor(private val mWatchaDao: WatchaDao) {
    companion object {
        private var INSTANCE: LocalDataSource?= null

        fun getInstance(watchaDao: WatchaDao): LocalDataSource =
                INSTANCE ?: LocalDataSource(watchaDao)
    }

    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity> = mWatchaDao.getPopularMovie()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mWatchaDao.getFavMovie()

    fun setFavoriteMovies(movie: MovieEntity, newState: Boolean) {
        movie.movieAdd = newState
        mWatchaDao.updateMovie(movie)
    }

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = mWatchaDao.getMovieById(movieId)

    fun insertMovie(movie: List<MovieEntity>) = mWatchaDao.insertMovie(movie)

    fun updateMovie(movie: MovieEntity) = mWatchaDao.updateMovie(movie)

    fun getPopularShows(): DataSource.Factory<Int, ShowEntity> = mWatchaDao.getPopularShow()

    fun getFavoriteShows(): DataSource.Factory<Int, ShowEntity> = mWatchaDao.getFavShow()

    fun setFavoriteShows(show: ShowEntity, newState: Boolean) {
        show.showAdd = newState
        mWatchaDao.updateShow(show)
    }

    fun getShowById(showId: Int): LiveData<ShowEntity> = mWatchaDao.getShowById(showId)

    fun insertShow(show: List<ShowEntity>) = mWatchaDao.insertShow(show)

    fun updateShow(show: ShowEntity) = mWatchaDao.updateShow(show)
}