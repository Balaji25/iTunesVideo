package com.bg.itunesvideo.data.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryViewModel(
   val repository: ITunesVideoRepository,
    application: Application
) : ViewModel() {

    suspend fun getHistoryVideoSongs() = withContext(Dispatchers.IO) { repository.getHistoryVideoSongs() }
}