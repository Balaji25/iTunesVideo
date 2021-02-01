package com.bg.itunesvideo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bg.itunesvideo.data.model.objects.VideoListObj
import com.bg.itunesvideo.data.model.objects.VideoObj

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
@Database(
    entities = [VideoObj::class],
    version = 1, exportSchema = false)


abstract class ITunesVideoDatabase : RoomDatabase(){

    abstract fun getVideoSongDao(): ITunesVideoDao

    companion object {

        @Volatile
        private var instance: ITunesVideoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ITunesVideoDatabase::class.java,
                "ITunesVideoDatabase.db"
            ).build()
    }
}