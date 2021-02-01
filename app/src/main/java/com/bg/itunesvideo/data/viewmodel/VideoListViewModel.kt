package com.bg.itunesvideo.data.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor
import com.bg.itunesvideo.data.model.objects.VideoListObj
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoListViewModel(
    val repository: ITunesVideoRepository,
    application: Application
) : ViewModel() {

    /** getVideoList method is use to fetch data from server
     *
     * */
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getVideoList(): MutableLiveData<VideoListObj> {

        return withContext(Dispatchers.IO) {
            repository.fetchVideoFeed()
        }
    }

    /** saveHistoryVideoSongs method is use to save video data to room db
     *
     * */
    suspend fun saveHistoryVideoSongs(videoObj: VideoObj) = withContext(Dispatchers.IO) { repository.saveVideoInfo(videoObj) }


}