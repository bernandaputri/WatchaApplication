package com.putri.watchaapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.vo.Resource

class DetailViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {

    private var movieId: Int? = 0
    private lateinit var movies: LiveData<Resource<MovieEntity>>

    private var showId: Int? = 0
    private lateinit var shows: LiveData<Resource<ShowEntity>>

    fun selectedMovie(movieId: Int) {
        this.movieId = movieId
        movies = watchaRepository.getDetailMovie(movieId)
    }

    fun getDetailMovie() = movies

    fun setFavMovie() {
        val favMovie = movies.value
        if (favMovie?.data != null) {
            val newState = !favMovie.data.movieAdd
            watchaRepository.setFavMovie(favMovie.data, newState)
        }
    }

    fun selectedTvShow(showId: Int) {
        this.showId = showId
        shows = watchaRepository.getDetailShow(showId)
    }

    fun getDetailShow() = shows

    fun setFavShow() {

        val favShow = shows.value
        if (favShow?.data != null) {
            val newState = !favShow.data.showAdd
            watchaRepository.setFavShow(favShow.data, newState)
        }
    }

}