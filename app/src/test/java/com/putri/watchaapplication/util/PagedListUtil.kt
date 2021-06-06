package com.putri.watchaapplication.util

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<Any> {
        val pagedList = mock(PagedList::class.java) as PagedList<Any>

        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }

}