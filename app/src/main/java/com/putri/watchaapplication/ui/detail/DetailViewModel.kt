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

    private var movieId: Int? = 0
    private lateinit var movies: LiveData<Resource<MovieEntity>>

    private var showId: Int? = 0
    private lateinit var shows: LiveData<Resource<ShowEntity>>

//    val movieId = MutableLiveData<Int>()

    fun selectedMovie(movieId: Int) {
        this.movieId = movieId
        movies = watchaRepository.getDetailMovie(movieId)
    }

    fun getDetailMovie() = movies

//    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { isMovieId ->
//        watchaRepository.getDetailMovie(isMovieId)
//    }

    fun setFavMovie() {
//        val favMovie = detailMovie.value
//        if (favMovie != null) {
//            val movies = favMovie.data
//            if (movies != null) {
//                val newState = !movies.movieAdd
//                watchaRepository.setFavMovie(movies, newState)
//            }
//        }
        val favMovie = movies.value
        if (favMovie?.data != null) {
            val newState = !favMovie.data.movieAdd
            watchaRepository.setFavMovie(favMovie.data, newState)
        }
    }

//    val showId = MutableLiveData<Int>()

    fun selectedTvShow(showId: Int) {
        this.showId = showId
        shows = watchaRepository.getDetailShow(showId)
    }

    fun getDetailShow() = shows

//    var detailShow: LiveData<Resource<ShowEntity>> = Transformations.switchMap(showId) { isShowId ->
//        watchaRepository.getDetailShow(isShowId)
//    }

    fun setFavShow() {
//        val favShow = detailShow.value
//        if (favShow != null) {
//            val shows = favShow.data
//            if (shows != null) {
//                val newState = !shows.showAdd
//                watchaRepository.setFavShow(shows, newState)
//            }
//        }
        val favShow = shows.value
        if (favShow?.data != null) {
            val newState = !favShow.data.showAdd
            watchaRepository.setFavShow(favShow.data, newState)
        }
    }

}