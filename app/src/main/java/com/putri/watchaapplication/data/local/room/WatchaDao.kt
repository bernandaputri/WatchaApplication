package com.putri.watchaapplication.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity

@Dao
interface WatchaDao {

    @Query("SELECT * FROM movie_entity")
    fun getPopularMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entity WHERE movieAdd = 1")
    fun getFavMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entity WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM show_entity")
    fun getPopularShow(): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM show_entity WHERE showAdd = 1")
    fun getFavShow(): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM show_entity WHERE showId = :showId")
    fun getShowById(showId: Int): LiveData<ShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(movies: List<ShowEntity>)

    @Update
    fun updateShow(show: ShowEntity)

}