package com.putri.watchaapplication.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.ShowEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito

class FavShowViewModelTest {

    private lateinit var favShowViewModel: FavShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<PagedList<ShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<ShowEntity>

    @Before
    fun setup() {
        favShowViewModel = FavShowViewModel(watchaRepository)
    }

    @Test
    fun getFavShow() {
        val dummyshow = pagedList
        Mockito.`when`(dummyshow.size).thenReturn(3)

        val shows = MutableLiveData<PagedList<ShowEntity>>()
        shows.value = dummyshow

        Mockito.`when`(watchaRepository.getFavShow()).thenReturn(shows)

        val showEntities = favShowViewModel.getFavShow().value
        verify(watchaRepository).getFavShow()

        assertNotNull(showEntities)
        assertEquals(3, showEntities?.size)

        favShowViewModel.getFavShow().observeForever(observer)
        verify(observer).onChanged(dummyshow)
    }
}