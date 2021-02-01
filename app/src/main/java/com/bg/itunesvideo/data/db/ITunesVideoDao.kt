package com.bg.itunesvideo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bg.itunesvideo.data.model.objects.VideoListObj
import com.bg.itunesvideo.data.model.objects.VideoObj

/**
 * Created by Balaji Gaikwad on 31/1/21.
 *
 */
@Dao
interface ITunesVideoDao {

    @Query("SELECT * FROM VideoObj")
    fun getOfflineVideoSongs() : LiveData<List<VideoObj>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVideoSong(ResultsItem : VideoObj)
}