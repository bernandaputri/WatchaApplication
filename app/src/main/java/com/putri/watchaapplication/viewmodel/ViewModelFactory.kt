package com.putri.watchaapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.putri.watchaapplication.data.WatchaRepository
import com.putri.watchaapplication.di.Injection
import com.putri.watchaapplication.ui.detail.DetailViewModel
import com.putri.watchaapplication.ui.favorite.movie.FavMovieViewModel
import com.putri.watchaapplication.ui.favorite.tvshow.FavShowViewModel
import com.putri.watchaapplication.ui.movie.MovieViewModel
import com.putri.watchaapplication.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mWatchaRepository: WatchaRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mWatchaRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mWatchaRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mWatchaRepository) as T
            }
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                return FavMovieViewModel(mWatchaRepository) as T
            }
            modelClass.isAssignableFrom(FavShowViewModel::class.java) -> {
                return FavShowViewModel(mWatchaRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}