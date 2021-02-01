package com.bg.itunesvideo.data.view.videoHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bg.itunesvideo.R
import com.bg.itunesvideo.data.adapter.VideoListAdapter
import com.bg.itunesvideo.data.callback.VideoCallback
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.utils.ApiException
import com.bg.itunesvideo.data.utils.Coroutines
import com.bg.itunesvideo.data.utils.NoInternetException
import com.bg.itunesvideo.data.viewmodel.HistoryViewModel
import com.bg.itunesvideo.data.viewmodel.HistoryViewModelFactory
import com.bg.itunesvideo.data.viewmodel.VideoListViewModelFactory
import com.bg.itunesvideo.databinding.FragmentVideoHistoryBinding
import com.bg.itunesvideo.databinding.FragmentVideoListBinding
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HistoryFragment : Fragment() , KodeinAware , VideoCallback {

    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dashboardViewModel: HistoryViewModel
    private val factory: HistoryViewModelFactory by instance()
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this,factory).get(HistoryViewModel::class.java)



        val fragmentVideoListBinding: FragmentVideoHistoryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_video_history, container, false
        )

// bind RecyclerView
        recyclerView = fragmentVideoListBinding?.recyclerViewVideo
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        //init the Custom adataper
        videoListAdapter = VideoListAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(videoListAdapter)

        return fragmentVideoListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMusicListData()
    }
    private fun initMusicListData()= Coroutines.main {
        try {
            var  musicFragmentContext=this

            lifecycleScope.launch {
                dashboardViewModel.getHistoryVideoSongs().observe(viewLifecycleOwner, Observer {

                    videoListAdapter?.setMusicList(
                        musicFragmentContext,
                        it
                    )
                })
            }

        } catch (e: ApiException) {
            e.printStackTrace()
        } catch (e: NoInternetException) {
            e.printStackTrace()
        }
    }

    override fun onClick(transaction: VideoObj) {

    }

}