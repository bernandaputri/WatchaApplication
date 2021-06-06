package com.putri.watchaapplication.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "show_entity")
data class ShowEntity(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "showId")
    var showId: Int?,

    @ColumnInfo(name = "showTitle")
    var showTitle: String?,

    @ColumnInfo(name = "showPoster")
    var showPoster: String?,

    @ColumnInfo(name = "showRating")
    var showRating: Double?,

    @ColumnInfo(name = "showDesc")
    var showDesc: String?,

    @ColumnInfo(name = "showRelease")
    var showRelease: String?,

    @ColumnInfo(name = "showAdd")
    var showAdd: Boolean = false
)