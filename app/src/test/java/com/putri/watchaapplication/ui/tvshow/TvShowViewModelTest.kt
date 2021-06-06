package com.putri.watchaapplication.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var showViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var watchaRepository: WatchaRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<ShowEntity>

    @Before
    fun setup() {
        showViewModel = TvShowViewModel(watchaRepository)
    }

    @Test
    fun getShows() {
        val dummyShow = Resource.success(pagedList)
        `when`(dummyShow.data?.size).thenReturn(3)

        val shows = MutableLiveData<Resource<PagedList<ShowEntity>>>()
        shows.value = dummyShow

        `when`(watchaRepository.getPopularShow()).thenReturn(shows)
        val showEntities = showViewModel.getShows().value?.data
        verify(watchaRepository).getPopularShow()
        assertNotNull(showEntities)
        assertEquals(3, showEntities?.size)

        showViewModel.getShows().observeForever(observer)
        verify(observer).onChanged(dummyShow)
    }
}