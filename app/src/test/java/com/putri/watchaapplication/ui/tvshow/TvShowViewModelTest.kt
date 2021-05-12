package com.putri.watchaapplication.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.utils.DataMedia
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var showViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<List<MediaEntity>>

    @Before
    fun setup() {
        showViewModel = TvShowViewModel(watchaRepository)
    }

    @Test
    fun getShows() {
        val dummyShow = DataMedia.setDummyShow()
        val shows = MutableLiveData<List<MediaEntity>>()
        shows.value = dummyShow

        Mockito.`when`(watchaRepository.getPopularShow()).thenReturn(shows)
        val showEntities = showViewModel.getShows().value
        verify(watchaRepository).getPopularShow()
        assertNotNull(showEntities)
        assertEquals(5, showEntities?.size)

        showViewModel.getShows().observeForever(observer)
        verify(observer).onChanged(dummyShow)
    }
}