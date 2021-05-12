package com.putri.watchaapplication.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.DetailMediaEntity
import com.putri.watchaapplication.utils.DataMedia
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    val dummyMovie = DataMedia.setDummyMovie()[0]
    private val movieId = dummyMovie.id
    private val dummyDetailMovie = DataMedia.setDetailDummyMovie(movieId as Int)[0]

    val dummyShow = DataMedia.setDummyShow()[0]
    private val showId = dummyShow.id
    private val dummyDetailShow = DataMedia.setDetailDummyShow(showId)[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<DetailMediaEntity>

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(watchaRepository)
        movieId?.let { detailViewModel.selectedMovie(it) }
        showId?.let { detailViewModel.selectedTvShow(it) }
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = MutableLiveData<DetailMediaEntity>()
        detailMovie.value = dummyDetailMovie

        `when`(watchaRepository.getDetailMovie(movieId as Int)).thenReturn(detailMovie)
        val detailMovieEntity = detailViewModel.getDetailMovie().value as DetailMediaEntity
        verify(watchaRepository).getDetailMovie(movieId)
        assertNotNull(detailMovieEntity)
        assertEquals(dummyDetailMovie.mediaId, detailMovieEntity.mediaId)
        assertEquals(dummyDetailMovie.mediaTitle, detailMovieEntity.mediaTitle)
        assertEquals(dummyDetailMovie.mediaPoster, detailMovieEntity.mediaPoster)
        assertEquals(dummyDetailMovie.mediaDesc, detailMovieEntity.mediaDesc)
        assertEquals(dummyDetailMovie.mediaRelease, detailMovieEntity.mediaRelease)
        assertEquals(dummyDetailMovie.mediaGenres, detailMovieEntity.mediaGenres)

        detailViewModel.getDetailMovie().observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)
    }

    @Test
    fun getDetailTvShow() {
        val detailShow = MutableLiveData<DetailMediaEntity>()
        detailShow.value = dummyDetailShow

        `when`(watchaRepository.getDetailTvShow(showId as Int)).thenReturn(detailShow)
        val detailShowEntity = detailViewModel.getDetailTvShow().value as DetailMediaEntity
        verify(watchaRepository).getDetailTvShow(showId)
        assertNotNull(detailShowEntity)
        assertEquals(dummyDetailShow.mediaId, detailShowEntity.mediaId)
        assertEquals(dummyDetailShow.mediaTitle, detailShowEntity.mediaTitle)
        assertEquals(dummyDetailShow.mediaPoster, detailShowEntity.mediaPoster)
        assertEquals(dummyDetailShow.mediaDesc, detailShowEntity.mediaDesc)
        assertEquals(dummyDetailShow.mediaRelease, detailShowEntity.mediaRelease)
        assertEquals(dummyDetailShow.mediaGenres, detailShowEntity.mediaGenres)

        detailViewModel.getDetailTvShow().observeForever(observer)
        verify(observer).onChanged(dummyDetailShow)
    }
}