package com.bg.itunesvideo.data.view

import android.widget.MediaController
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bg.itunesvideo.R
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.utils.AppConstants
import com.bg.itunesvideo.data.utils.VideoUtils
import com.bg.itunesvideo.data.viewmodel.VideoDetailsViewModel
import com.bg.itunesvideo.data.viewmodel.VideoDetailsViewModelFactory
import com.bg.itunesvideo.databinding.FragmentVideoDetailsBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
class VideoDetailsFragment : Fragment(), KodeinAware {

    private lateinit var videoDetailsViewModel: VideoDetailsViewModel
    private lateinit var videoItem: VideoObj

    private val factory: VideoDetailsViewModelFactory by instance()
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setPrimaryColorStatusBarColor()
        videoDetailsViewModel = ViewModelProvider(this, factory).get(VideoDetailsViewModel::class.java)


        val fragmentMusicDetailsBinding: FragmentVideoDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_video_details, container, false
        )

        if (arguments!=null && arguments?.getSerializable(AppConstants.EXTRA_VIDEO_OBJ)!=null){
            videoItem= arguments?.getSerializable(AppConstants.EXTRA_VIDEO_OBJ) as VideoObj

        }
        fragmentMusicDetailsBinding.videoModel=videoItem

        fragmentMusicDetailsBinding.videoUtils= VideoUtils



        ///Setting MediaController
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(fragmentMusicDetailsBinding.videoView)
        fragmentMusicDetailsBinding.videoView.setVideoPath(videoItem.previewUrl)
        fragmentMusicDetailsBinding.videoView.setMediaController(mediaController)
        fragmentMusicDetailsBinding.videoView.requestFocus()
        fragmentMusicDetailsBinding.videoView.start()

        return fragmentMusicDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null && arguments?.getSerializable(AppConstants.EXTRA_VIDEO_OBJ)!=null){
            videoItem= arguments?.getSerializable(AppConstants.EXTRA_VIDEO_OBJ) as VideoObj

        }
    }

}