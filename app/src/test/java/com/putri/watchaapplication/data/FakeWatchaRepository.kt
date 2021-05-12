package com.putri.watchaapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.putri.watchaapplication.data.entity.DetailMediaEntity
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.data.remote.RemoteDataSource
import com.putri.watchaapplication.data.remote.response.DetailMovieResponse
import com.putri.watchaapplication.data.remote.response.DetailTvShowResponse
import com.putri.watchaapplication.data.remote.response.MovieResultsItem
import com.putri.watchaapplication.data.remote.response.TvShowResultsItem
import com.putri.watchaapplication.helper.GenresHelper.genreMovie
import com.putri.watchaapplication.helper.GenresHelper.genreTvShow

class FakeWatchaRepository (private val remoteDataSource: RemoteDataSource) : WatchaDataSource {

    override fun getPopularMovie(): LiveData<List<MediaEntity>> {
        val movieData = MutableLiveData<List<MediaEntity>>()

        remoteDataSource.getPopularMovie(object : RemoteDataSource.LoadPopularMovie {
            override fun popularMovie(isPopularMovie: List<MovieResultsItem>?) {
                val movieList = ArrayList<MediaEntity>()

                if (isPopularMovie != null) {
                    for (response in isPopularMovie) {
                        val popularMovie = MediaEntity (
                            response.id,
                            response.title,
                            response.posterPath,
                            response.voteAverage
                        )
                        movieList.add(popularMovie)
                    }
                }
                movieData.postValue(movieList)
            }
        })
        return movieData
    }

    override fun getPopularShow(): LiveData<List<MediaEntity>> {
        val movieData = MutableLiveData<List<MediaEntity>>()

        remoteDataSource.getPopularShow(object : RemoteDataSource.LoadPopularShow {
            override fun popularShow(isPopularShow: List<TvShowResultsItem>?) {
                val showList = ArrayList<MediaEntity>()

                if (isPopularShow != null) {
                    for (response in isPopularShow) {
                        val popularShow = MediaEntity (
                            response.id,
                            response.name,
                            response.posterPath,
                            response.voteAverage
                        )
                        showList.add(popularShow)
                    }
                }
                movieData.postValue(showList)
            }
        })
        return movieData
    }

    override fun getDetailMovie(mediaId: Int) : LiveData<DetailMediaEntity> {
        var dataDetailMovie = MutableLiveData<DetailMediaEntity>()

        remoteDataSource.getDetailMovie(mediaId, object : RemoteDataSource.LoadDetailMovie {
            override fun detailMovie(detailMovie: DetailMovieResponse?) {
                if (detailMovie != null) {
                    val theDetailMovie = DetailMediaEntity (
                            detailMovie.id,
                            detailMovie.title,
                            detailMovie.posterPath,
                            detailMovie.overview,
                            detailMovie.releaseDate,
                            genreMovie(detailMovie).toString()
                    )
                    dataDetailMovie.postValue(theDetailMovie)
                }
            }
        })
        return dataDetailMovie
    }

    override fun getDetailTvShow(mediaId: Int) : LiveData<DetailMediaEntity> {
        var dataDetailTvShow = MutableLiveData<DetailMediaEntity>()

        remoteDataSource.getDetailTvShow(mediaId, object : RemoteDataSource.LoadDetailTvShow {
            override fun detailTvShow(detailTvShow: DetailTvShowResponse?){
                if (detailTvShow != null) {
                    val theDetailTvShow = DetailMediaEntity (
                            detailTvShow.id,
                            detailTvShow.name,
                            detailTvShow.posterPath,
                            detailTvShow.overview,
                            detailTvShow.firstAirDate,
                            genreTvShow(detailTvShow).toString()
                    )
                    dataDetailTvShow.postValue(theDetailTvShow)
                }
            }
        })
        return dataDetailTvShow
    }
}