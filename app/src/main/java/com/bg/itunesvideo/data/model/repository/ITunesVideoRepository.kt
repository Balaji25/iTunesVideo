package com.bg.itunesvideo.data.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bg.imusicplayer.data.network.AbstractTaskApiRequest
import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor
import com.bg.itunesvideo.data.db.ITunesVideoDatabase
import com.bg.itunesvideo.data.model.objects.VideoListObj
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.network.ApiEndPoint
import com.bg.itunesvideo.data.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Balaji Gaikwad on 31/1/21.
 *
 * This is ITunesVideoRepository class used to get dat from web and save recently visited videos to database
 *
 */
class ITunesVideoRepository(val apiEndPoint: ApiEndPoint, val db: ITunesVideoDatabase,val networkConnectionInterceptor: NetworkConnectionInterceptor): AbstractTaskApiRequest() {

    private val videoFeedLiveData = MutableLiveData<VideoListObj>()

/**
 * This fetchVideoFeed method is use to get data from server
 * it returns live data object of VideoListObj
 *
 * */
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun fetchVideoFeed(): MutableLiveData<VideoListObj> {
        return withContext(Dispatchers.IO) {
            if (networkConnectionInterceptor.isInternetAvailable()) {
                var response = apiRequest { apiEndPoint.fetchVideoSongs() }
                videoFeedLiveData.postValue(response)
                videoFeedLiveData
            }else{
                videoFeedLiveData
            }
        }
    }

    /**
     * This saveVideoInfo method is use to save particular video data to room db
     *
     * */
    fun saveVideoInfo(resultsItem: VideoObj) {
        Coroutines.io {
            db.getVideoSongDao().saveVideoSong(resultsItem)
        }
    }


    /**
     * This getHistoryVideoSongs method is use to get all videos
     * save in th room db to show on history screen
     *
     * */
    suspend fun getHistoryVideoSongs(): LiveData<List<VideoObj>> {
        return withContext(Dispatchers.IO) {
            db.getVideoSongDao().getOfflineVideoSongs()
        }
    }



}