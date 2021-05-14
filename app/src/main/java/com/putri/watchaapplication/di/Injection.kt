package com.putri.watchaapplication.di

import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): WatchaRepository {

        val remoteDataSource = RemoteDataSource()

        return WatchaRepository.getInstance(remoteDataSource)

    }
}