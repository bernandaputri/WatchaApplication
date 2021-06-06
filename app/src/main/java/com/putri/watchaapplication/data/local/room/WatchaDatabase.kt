package com.putri.watchaapplication.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity

@Database(
        entities = [MovieEntity::class, ShowEntity::class],
        version = 1,
        exportSchema = false
)
abstract class WatchaDatabase : RoomDatabase() {

    abstract fun watchaDao() : WatchaDao

    companion object {
        @Volatile
        private var INSTANCE: WatchaDatabase? = null

        fun getInstance(context: Context): WatchaDatabase =
                INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            WatchaDatabase::class.java,
                            "wathca.db"
                    ).build().apply { INSTANCE = this }
                }
    }
}