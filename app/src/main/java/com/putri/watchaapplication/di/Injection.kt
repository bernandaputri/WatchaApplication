package com.putri.watchaapplication.di

import android.content.Context
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.data.local.LocalDataSource
import com.putri.watchaapplication.data.local.room.WatchaDatabase
import com.putri.watchaapplication.data.remote.RemoteDataSource
import com.putri.watchaapplication.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): WatchaRepository {

        val database = WatchaDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.watchaDao())
        val appExecutors = AppExecutors()

        return WatchaRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}