package com.putri.watchaapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.*
import com.putri.watchaapplication.data.local.LocalDataSource
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.data.remote.RemoteDataSource
import com.putri.watchaapplication.util.PagedListUtil
import com.putri.watchaapplication.utils.AppExecutors
import com.putri.watchaapplication.utils.DataMedia
import com.putri.watchaapplication.utils.LiveDataTestUtil
import com.putri.watchaapplication.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class WatchaRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val watchaRepository = FakeWatchaRepository(remoteDataSource, localDataSource, appExecutors)

    private val movieResponse = DataMedia.setRemoteDummyMovie()
    private val movieId = movieResponse[1].id
    private val showResponse = DataMedia.setRemoteDummyShow()
    private val showId = showResponse[1].id

    private val detailMovieResponse = DataMedia.setDetailRemoteDummyMovie(movieId)
    private val detailShowResponse = DataMedia.setDetailRemoteDummyShow(showId)

    @Test
    fun getPopularMovie() {

        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getPopularMovies()).thenReturn(dataSource)

        watchaRepository.getPopularMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataMedia.setDummyMovie()))
        verify(localDataSource).getPopularMovies()

        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getDetailMovie() {

        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DataMedia.setDetailDummyMovie()

        `when`(localDataSource.getMovieById(movieId)).thenReturn(dummyDetailMovie)

        val detailMovieEntities = LiveDataTestUtil.getValue(watchaRepository.getDetailMovie(movieId))
        verify(localDataSource).getMovieById(movieId)

        assertNotNull(detailMovieEntities)
        assertEquals(detailMovieResponse.id, detailMovieEntities.data?.movieId)
        assertEquals(detailMovieResponse.title, detailMovieEntities.data?.movieTitle)
        assertEquals(detailMovieResponse.posterPath, detailMovieEntities.data?.moviePoster)
        assertEquals(detailMovieResponse.overview, detailMovieEntities.data?.movieDesc)
        assertEquals(detailMovieResponse.releaseDate, detailMovieEntities.data?.movieRelease)
        assertEquals(detailMovieResponse.voteAverage, detailMovieEntities.data?.movieRating)
    }

    @Test
    fun getPopularShow() {

        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowEntity>
        `when`(localDataSource.getPopularShows()).thenReturn(dataSource)

        watchaRepository.getPopularShow()

        val showEntities = Resource.success(PagedListUtil.mockPagedList(DataMedia.setDummyShow()))
        verify(localDataSource).getPopularShows()

        assertNotNull(showEntities.data)
        assertEquals(showResponse.size.toLong(), showEntities.data?.size?.toLong())

    }

    @Test
    fun getDetailTvShow() {

        val dummyDetailShow = MutableLiveData<ShowEntity>()
        dummyDetailShow.value = DataMedia.setDetailDummyShow()

        `when`(localDataSource.getShowById(showId)).thenReturn(dummyDetailShow)

        val detailShowEntities = LiveDataTestUtil.getValue(watchaRepository.getDetailShow(showId))
        verify(localDataSource).getShowById(showId)

        assertNotNull(detailShowEntities)
        assertEquals(detailShowResponse.id, detailShowEntities.data?.showId)
        assertEquals(detailShowResponse.name, detailShowEntities.data?.showTitle)
        assertEquals(detailShowResponse.posterPath, detailShowEntities.data?.showPoster)
        assertEquals(detailShowResponse.overview, detailShowEntities.data?.showDesc)
        assertEquals(detailShowResponse.firstAirDate, detailShowEntities.data?.showRelease)
        assertEquals(detailShowResponse.voteAverage, detailShowEntities.data?.showRating)
    }

    @Test
    fun getFavMovie() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getFavoriteMovies()).thenReturn(dataSource)

        watchaRepository.getFavMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataMedia.setDummyMovie()))
        verify(localDataSource).getFavoriteMovies()

        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavShow() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowEntity>
        `when`(localDataSource.getFavoriteShows()).thenReturn(dataSource)

        watchaRepository.getFavShow()

        val showEntities = Resource.success(PagedListUtil.mockPagedList(DataMedia.setDummyShow()))
        verify(localDataSource).getFavoriteShows()

        assertNotNull(showEntities.data)
        assertEquals(showResponse.size.toLong(), showEntities.data?.size?.toLong())
    }
}