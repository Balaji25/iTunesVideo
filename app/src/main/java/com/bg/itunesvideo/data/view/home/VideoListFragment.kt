package com.bg.itunesvideo.data.view.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor

import com.bg.itunesvideo.R
import com.bg.itunesvideo.data.adapter.VideoListAdapter
import com.bg.itunesvideo.data.callback.VideoCallback
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.utils.ApiException
import com.bg.itunesvideo.data.utils.AppConstants.EXTRA_VIDEO_OBJ
import com.bg.itunesvideo.data.utils.Coroutines
import com.bg.itunesvideo.data.utils.NoInternetException
import com.bg.itunesvideo.data.viewmodel.VideoListViewModel
import com.bg.itunesvideo.data.viewmodel.VideoListViewModelFactory
import com.bg.itunesvideo.databinding.FragmentVideoListBinding
import kotlinx.android.synthetic.main.fragment_video_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class VideoListFragment : Fragment(), KodeinAware,VideoCallback {

    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoListViewModel: VideoListViewModel

    private val factory: VideoListViewModelFactory by instance()
    override val kodein by kodein()

    val networkConnection: NetworkConnectionInterceptor by instance()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        videoListViewModel =
            ViewModelProvider(this, factory).get(VideoListViewModel::class.java)

        val fragmentVideoListBinding: FragmentVideoListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_video_list, container, false
        )

        // bind RecyclerView
        recyclerView = fragmentVideoListBinding?.recyclerViewVideo
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        //init the Custom adataper
        videoListAdapter = VideoListAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(videoListAdapter)

        initSwipeListener(fragmentVideoListBinding.itemsswipetorefresh)

        return fragmentVideoListBinding.root
    }

    /** This method initSwipeListener is used to refresh data with swipe to refresh
     *
     * */
    @RequiresApi(Build.VERSION_CODES.M)
    fun initSwipeListener(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            // Your code here
            error_textView.visibility=View.GONE
            swipeRefreshLayout.isRefreshing=false
            initMusicListData()
        })
        // Scheme colors for animation
        swipeRefreshLayout.setColorSchemeColors(
            getResources().getColor(android.R.color.holo_blue_bright),
            getResources().getColor(android.R.color.holo_green_light),
            getResources().getColor(android.R.color.holo_orange_light),
            getResources().getColor(android.R.color.holo_red_light)
        );

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMusicListData()
    }

    /** This method initMusicListData is used to fetch data from viewmodel
     *
     * */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initMusicListData()= Coroutines.main {
        try {
            var  musicFragmentContext=this
            if (networkConnection.isInternetAvailable()) {
                lifecycleScope.launch {
                    videoListViewModel.getVideoList().observe(viewLifecycleOwner, Observer {
                        it?.let { it1 ->
                            it1.results.let { it2 ->

                                videoListAdapter.setMusicList(
                                    musicFragmentContext,
                                    it2

                                )
                            }
                        }
                    })
                }

            }else{
               error_textView.visibility=View.VISIBLE
            }

        } catch (e: ApiException) {
            e.printStackTrace()
        } catch (e: NoInternetException) {
            e.printStackTrace()
        }
    }

    /** This method onClick is used to fnavigate to the video details
     *
     * */
    override fun onClick(videoObj: VideoObj) {
        var bundle = Bundle()
        bundle.putSerializable(EXTRA_VIDEO_OBJ, videoObj)
        findNavController().navigate(
            R.id.action_navigation_home_to_video_details,
            bundle
        )
        saveToFavourite(videoObj)
    }


    /**  @method saveToFavourite is used to save video data to local db when click on particular  video list item
     * @param videoObj contains
     */
    private fun saveToFavourite(videoObj: VideoObj) = Coroutines.main {
        try {
            lifecycleScope.launch {
                videoListViewModel.saveHistoryVideoSongs(videoObj)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}