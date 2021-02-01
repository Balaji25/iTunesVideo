package com.bg.itunesvideo.data.callback

import com.bg.itunesvideo.data.model.objects.VideoObj

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
interface VideoCallback {
    fun onClick(videoObj: VideoObj)
}