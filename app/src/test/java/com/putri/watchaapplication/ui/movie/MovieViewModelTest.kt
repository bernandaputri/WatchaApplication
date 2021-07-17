package com.putri.watchaapplication.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(watchaRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(3)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(watchaRepository.getPopularMovie()).thenReturn(movies)

        val movieEntities = movieViewModel.getMovies().value?.data
        verify(watchaRepository).getPopularMovie()

        assertNotNull(movieEntities)
        assertEquals(3, movieEntities?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}