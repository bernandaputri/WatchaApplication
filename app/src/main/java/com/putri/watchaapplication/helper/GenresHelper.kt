package com.putri.watchaapplication.helper

import com.putri.watchaapplication.data.remote.response.DetailMovieResponse
import com.putri.watchaapplication.data.remote.response.DetailTvShowResponse

object GenresHelper {
    fun genreMovie(detailMovieResponse: DetailMovieResponse): StringBuilder {
        val listGenreMovie = StringBuilder()
        var separator = false
        val genreMap = detailMovieResponse.genres.map {
            if (it.name.isBlank()) "-" else it.name
        }
        for (genre in genreMap) {
            if (separator) listGenreMovie.append("\n")
            separator = true
            listGenreMovie.append(genre)
        }
        return listGenreMovie
    }

    fun genreTvShow(detailTvShowResponse: DetailTvShowResponse): StringBuilder {
        val listGenreTvShow = StringBuilder()
        var separator = false
        val genreMap = detailTvShowResponse.genres.map {
            if (it.name.isBlank()) "-" else it.name
        }
        for (genre in genreMap) {
            if (separator) listGenreTvShow.append("\n")
            separator = true
            listGenreTvShow.append(genre)
        }
        return listGenreTvShow
    }
}