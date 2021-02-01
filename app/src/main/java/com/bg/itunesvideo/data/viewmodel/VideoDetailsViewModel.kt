package com.bg.itunesvideo.data.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
class VideoDetailsViewModel (
    val repository: ITunesVideoRepository,
    application: Application
) : ViewModel(){


}