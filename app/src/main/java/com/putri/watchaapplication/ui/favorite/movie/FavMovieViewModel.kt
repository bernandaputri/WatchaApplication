package com.putri.watchaapplication.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity

class FavMovieViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {

    fun getFavMovies() : LiveData<PagedList<MovieEntity>> = watchaRepository.getFavMovie()

    fun setFavMovie(movie: MovieEntity) {
        val newState = !movie.movieAdd
        watchaRepository.setFavMovie(movie, newState)
    }
}