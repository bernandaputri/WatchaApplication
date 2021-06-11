package com.putri.watchaapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.vo.Resource

class DetailViewModel (private val watchaRepository: WatchaRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()
    val showId = MutableLiveData<Int>()

//    private var movieId: Int? = 0
//    private lateinit var movies: LiveData<Resource<MovieEntity>>
//
//    private var showId: Int? = 0
//    private lateinit var shows: LiveData<Resource<ShowEntity>>
//
    fun selectedMovie(movieId: Int) {
        this.movieId.value = movieId
//        movies = watchaRepository.getDetailMovie(movieId)
    }
//
//    fun getDetailMovie() = movies
//
    fun setFavMovie() {
        val favMovie = detailMovie.value
        if (favMovie?.data != null) {
            val newState = !favMovie.data.movieAdd
            watchaRepository.setFavMovie(favMovie.data, newState)
        }
    }
//
    fun selectedTvShow(showId: Int) {
        this.showId.value = showId
//        shows = watchaRepository.getDetailShow(showId)
    }

    var detailShow: LiveData<Resource<ShowEntity>> = Transformations.switchMap(showId) { showId ->
        watchaRepository.getDetailShow(showId)
    }

    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { movieId ->
        watchaRepository.getDetailMovie(movieId)
    }

//
//    fun getDetailShow() = shows
//
    fun setFavShow() {

        val favShow = detailShow.value
        if (favShow?.data != null) {
            val newState = !favShow.data.showAdd
            watchaRepository.setFavShow(favShow.data, newState)
        }
    }

}