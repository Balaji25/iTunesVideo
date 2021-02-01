package com.bg.itunesvideo.data.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
class HistoryViewModelFactory(val repository: ITunesVideoRepository, val application: Application) : ViewModelProvider.NewInstanceFactory() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryViewModel(repository,application) as T
    }

}