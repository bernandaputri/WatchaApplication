package com.putri.watchaapplication.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.utils.DataMedia
import com.putri.watchaapplication.vo.Resource
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovie = DataMedia.setDetailDummyMovie()
    private val movieId = dummyMovie.movieId

    private val dummyShow = DataMedia.setDetailDummyShow()
    private val showId = dummyShow.showId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var showObserver: Observer<Resource<ShowEntity>>

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(watchaRepository)
        detailViewModel.selectedMovie(movieId as Int)
        detailViewModel.selectedTvShow(showId as Int)
        detailViewModel.setFavMovie()
        detailViewModel.setFavShow()
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(dummyMovie)
        val detailMovie = MutableLiveData<Resource<MovieEntity>>()
        detailMovie.value = dummyDetailMovie

        `when`(watchaRepository.getDetailMovie(movieId as Int)).thenReturn(detailMovie)

        detailViewModel.detailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)

    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailShow = Resource.success(dummyShow)
        val detailShow = MutableLiveData<Resource<ShowEntity>>()
        detailShow.value = dummyDetailShow

        detailViewModel.detailShow.observeForever(showObserver)
        verify(showObserver).onChanged(dummyDetailShow)
    }
}