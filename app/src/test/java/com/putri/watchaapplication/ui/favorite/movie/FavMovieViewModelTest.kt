package com.putri.watchaapplication.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.MovieEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito

class FavMovieViewModelTest {

    private lateinit var favMovieViewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        favMovieViewModel = FavMovieViewModel(watchaRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovies = pagedList
        Mockito.`when`(dummyMovies.size).thenReturn(3)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(watchaRepository.getFavMovie()).thenReturn(movies)

        val movieEntities = favMovieViewModel.getFavMovies().value
        verify(watchaRepository).getFavMovie()

        assertNotNull(movieEntities)
        assertEquals(3, movieEntities?.size)

        favMovieViewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}