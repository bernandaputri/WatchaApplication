package com.putri.watchaapplication.data

import androidx.lifecycle.LiveData
import com.putri.watchaapplication.data.entity.DetailMediaEntity
import com.putri.watchaapplication.data.entity.MediaEntity

interface WatchaDataSource {
    fun getPopularMovie() : LiveData<List<MediaEntity>>

    fun getPopularShow() : LiveData<List<MediaEntity>>

    fun getDetailMovie(mediaId: Int) : LiveData<DetailMediaEntity>

    fun getDetailTvShow(mediaId: Int) : LiveData<DetailMediaEntity>
}