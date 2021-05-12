package com.putri.watchaapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.data.remote.RemoteDataSource
import com.putri.watchaapplication.helper.GenresHelper.genreMovie
import com.putri.watchaapplication.helper.GenresHelper.genreTvShow
import com.putri.watchaapplication.utils.DataMedia
import com.putri.watchaapplication.utils.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

import org.mockito.Mockito
import org.mockito.Mockito.`when`

class WatchaRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val watchaRepository = FakeWatchaRepository(remoteDataSource)

    private val movieResponse = DataMedia.setRemoteDummyMovie()
    private val movieId = movieResponse[0].id
    private val showResponse = DataMedia.setRemoteDummyShow()
    private val showId = showResponse[0].id

    private val detailMovieResponse = DataMedia.setDetailRemoteDummyMovie(movieId)[0]
    private val detailShowResponse = DataMedia.setDetailRemoteDummyShow(showId)[0]

    @Test
    fun getPopularMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularMovie)
                    .popularMovie(movieResponse)
            null
        }.`when`(remoteDataSource).getPopularMovie(any())

        val movieEntities = LiveDataTestUtil.getValue(watchaRepository.getPopularMovie())
        verify(remoteDataSource).getPopularMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovie)
                    .detailMovie(detailMovieResponse)
            null
        }.`when`(remoteDataSource).getDetailMovie(eq(movieId), any())

        val detailMovieEntities = LiveDataTestUtil.getValue(watchaRepository.getDetailMovie(movieId))
        verify(remoteDataSource).getDetailMovie(eq(movieId), any())
        assertNotNull(detailMovieEntities)
        assertEquals(detailMovieResponse.id, detailMovieEntities.mediaId)
        assertEquals(detailMovieResponse.title, detailMovieEntities.mediaTitle)
        assertEquals(detailMovieResponse.posterPath, detailMovieEntities.mediaPoster)
        assertEquals(detailMovieResponse.overview, detailMovieEntities.mediaDesc)
        assertEquals(detailMovieResponse.releaseDate, detailMovieEntities.mediaRelease)
        assertEquals(genreMovie(detailMovieResponse).toString(), detailMovieEntities.mediaGenres)
    }

    @Test
    fun getPopularShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularShow)
                    .popularShow(showResponse)
            null
        }.`when`(remoteDataSource).getPopularShow(any())

        val showEntities = LiveDataTestUtil.getValue(watchaRepository.getPopularShow())
        verify(remoteDataSource).getPopularShow(any())
        assertNotNull(showEntities)
        assertEquals(showResponse.size.toLong(), showEntities.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShow)
                    .detailTvShow(detailShowResponse)
            null
        }.`when`(remoteDataSource).getDetailTvShow(eq(showId), any())

        val detailShowEntities = LiveDataTestUtil.getValue(watchaRepository.getDetailTvShow(showId))
        verify(remoteDataSource).getDetailTvShow(eq(showId), any())
        assertNotNull(detailShowEntities)
        assertEquals(detailShowResponse.id, detailShowEntities.mediaId)
        assertEquals(detailShowResponse.name, detailShowEntities.mediaTitle)
        assertEquals(detailShowResponse.posterPath, detailShowEntities.mediaPoster)
        assertEquals(detailShowResponse.overview, detailShowEntities.mediaDesc)
        assertEquals(detailShowResponse.firstAirDate, detailShowEntities.mediaRelease)
        assertEquals(genreTvShow(detailShowResponse).toString(), detailShowEntities.mediaGenres)
    }
}