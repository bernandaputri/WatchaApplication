package com.putri.watchaapplication.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity (
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "movieId")
    var movieId: Int?,

    @ColumnInfo(name = "movieTitle")
    var movieTitle: String?,

    @ColumnInfo(name = "moviePoster")
    var moviePoster: String?,

    @ColumnInfo(name = "movieRating")
    var movieRating: Double?,

    @ColumnInfo(name = "movieDesc")
    var movieDesc: String?,

    @ColumnInfo(name = "movieRelease")
    var movieRelease: String?,

    @ColumnInfo(name = "movieAdd")
    var movieAdd: Boolean = false
)