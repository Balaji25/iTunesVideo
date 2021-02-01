package com.bg.itunesvideo.data.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
class VideoListViewModelFactory(val repository: ITunesVideoRepository, val application: Application) : ViewModelProvider.NewInstanceFactory() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoListViewModel(repository,application) as T
    }

}