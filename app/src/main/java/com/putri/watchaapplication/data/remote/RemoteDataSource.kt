package com.putri.watchaapplication.data.remote

import android.util.Log
import com.putri.watchaapplication.data.remote.response.*
import com.putri.watchaapplication.network.ApiConfig
import com.putri.watchaapplication.utils.EspressoIdlingResource.decrement
import com.putri.watchaapplication.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getPopularMovie(callback: LoadPopularMovie) {
        increment()

        val client = ApiConfig.getApiService().getMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    callback.popularMovie(response.body()?.results)
                    decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
    }

    fun getPopularShow(callback: LoadPopularShow) {
        increment()

        val client = ApiConfig.getApiService().getTvShow()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    callback.popularShow(response.body()?.results)
                    decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
    }

    fun getDetailMovie(movieId: Int, callback: LoadDetailMovie) {
        increment()

        val client = ApiConfig.getApiService().getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    callback.detailMovie(response.body())
                    decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
    }

    fun getDetailTvShow(showId: Int, callback: LoadDetailTvShow) {
        increment()

        val client = ApiConfig.getApiService().getDetailTvShow(showId)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                if (response.isSuccessful) {
                    callback.detailTvShow(response.body())
                    decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
    }

    interface LoadDetailTvShow {
        fun detailTvShow(detailTvShow: DetailTvShowResponse?)
    }

    interface LoadDetailMovie {
        fun detailMovie(detailMovie: DetailMovieResponse?)
    }

    interface LoadPopularShow {
        fun popularShow(isPopularShow : List<TvShowResultsItem>?)
    }

    interface LoadPopularMovie {
        fun popularMovie(isPopularMovie: List<MovieResultsItem>?)
    }
}