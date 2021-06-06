package com.putri.watchaapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.putri.watchaapplication.data.local.LocalDataSource
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.data.remote.ApiResponse
import com.putri.watchaapplication.data.remote.RemoteDataSource
import com.putri.watchaapplication.data.remote.response.DetailMovieResponse
import com.putri.watchaapplication.data.remote.response.DetailTvShowResponse
import com.putri.watchaapplication.data.remote.response.MovieResultsItem
import com.putri.watchaapplication.data.remote.response.TvShowResultsItem
import com.putri.watchaapplication.helper.GenresHelper.genreMovie
import com.putri.watchaapplication.helper.GenresHelper.genreTvShow
import com.putri.watchaapplication.utils.AppExecutors
import com.putri.watchaapplication.vo.Resource

class FakeWatchaRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : WatchaDataSource {

    override fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(movieResponse: List<MovieResultsItem>) {
                val movieData = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity (
                        response.id,
                        response.title,
                        response.posterPath,
                        response.voteAverage,
                        response.overview,
                        response.releaseDate,
                        false
                    )
                    movieData.add(movie)
                }
                localDataSource.insertMovie(movieData)
            }
        }.asLiveData()
    }

    override fun getPopularShow(): LiveData<Resource<PagedList<ShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<ShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<ShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getPopularShow()

            override fun saveCallResult(showResponse: List<TvShowResultsItem>) {
                val showData = ArrayList<ShowEntity>()
                for (response in showResponse) {
                    val show = ShowEntity (
                        response.id,
                        response.name,
                        response.posterPath,
                        response.voteAverage,
                        response.overview,
                        response.firstAirDate,
                        false
                    )
                    showData.add(show)
                }
                localDataSource.insertShow(showData)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int) : LiveData<Resource<MovieEntity>> {
        return object :
            NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(detailMovieResponse: DetailMovieResponse) {
                val detailMovie = MovieEntity (
                    detailMovieResponse.id,
                    detailMovieResponse.title,
                    detailMovieResponse.posterPath,
                    detailMovieResponse.voteAverage,
                    detailMovieResponse.overview,
                    detailMovieResponse.releaseDate,
                    false
                )
                localDataSource.updateMovie(detailMovie)
            }
        }.asLiveData()
    }

    override fun getDetailShow(showId: Int) : LiveData<Resource<ShowEntity>> {
        return object :
            NetworkBoundResource<ShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<ShowEntity> =
                localDataSource.getShowById(showId)

            override fun shouldFetch(data: ShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(showId)

            override fun saveCallResult(detailTvShowResponse: DetailTvShowResponse) {
                val detailShow = ShowEntity (
                    detailTvShowResponse.id,
                    detailTvShowResponse.name,
                    detailTvShowResponse.posterPath,
                    detailTvShowResponse.voteAverage,
                    detailTvShowResponse.overview,
                    detailTvShowResponse.firstAirDate,
                    false
                )
                localDataSource.updateShow(detailShow)
            }
        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavShow(): LiveData<PagedList<ShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteShows(), config).build()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovies(movie, state)
        }

    override fun setFavShow(tvShow: ShowEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteShows(tvShow, state)
        }

}