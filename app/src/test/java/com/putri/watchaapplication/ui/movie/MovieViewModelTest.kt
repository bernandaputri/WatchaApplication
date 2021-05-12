package com.putri.watchaapplication.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.utils.DataMedia
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<List<MediaEntity>>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(watchaRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = DataMedia.setDummyMovie()
        val movies = MutableLiveData<List<MediaEntity>>()
        movies.value = dummyMovie

        `when`(watchaRepository.getPopularMovie()).thenReturn(movies)
        val movieEntities = movieViewModel.getMovies().value
        verify(watchaRepository).getPopularMovie()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}