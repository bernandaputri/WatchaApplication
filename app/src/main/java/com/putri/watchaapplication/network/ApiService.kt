package com.putri.watchaapplication.network

import com.putri.watchaapplication.BuildConfig
import com.putri.watchaapplication.data.remote.response.DetailMovieResponse
import com.putri.watchaapplication.data.remote.response.DetailTvShowResponse
import com.putri.watchaapplication.data.remote.response.MovieResponse
import com.putri.watchaapplication.data.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    companion object {
        const val API_KEY = BuildConfig.TMDB_API_KEY
    }

    @GET("movie/popular?api_key=$API_KEY&page=1")
    fun getMovie(): Call<MovieResponse>

    @GET("tv/popular?api_key=$API_KEY&page=1")
    fun getTvShow(): Call<TvShowResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int
    ): Call<DetailMovieResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailTvShow(
        @Path("tv_id") tv_id: Int
    ): Call<DetailTvShowResponse>

}