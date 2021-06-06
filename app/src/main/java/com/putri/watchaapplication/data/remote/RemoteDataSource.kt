package com.putri.watchaapplication.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
            instance ?: RemoteDataSource().apply { instance = this }
        }

        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getPopularMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        increment()
        val listPopularMovie = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()

        val client = ApiConfig.getApiService().getMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    listPopularMovie.value =
                            ApiResponse.success(response.body()?.results!!)
                    decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
        return listPopularMovie
    }

    fun getPopularShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        increment()
        val listPopularShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()

        val client = ApiConfig.getApiService().getTvShow()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    listPopularShow.value =
                            ApiResponse.success(response.body()?.results!!)
                    decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
        return listPopularShow
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        increment()
        val listDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        val client = ApiConfig.getApiService().getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    listDetailMovie.value =
                            ApiResponse.success(response.body()!!)
                    decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
        return listDetailMovie
    }

    fun getDetailTvShow(showId: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        increment()
        val listDetailShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()

        val client = ApiConfig.getApiService().getDetailTvShow(showId)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                if (response.isSuccessful) {
                    listDetailShow.value =
                            ApiResponse.success(response.body()!!)
                    decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
            }
        })
        return listDetailShow
    }

}