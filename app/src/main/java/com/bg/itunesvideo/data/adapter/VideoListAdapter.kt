package com.bg.itunesvideo.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bg.itunesvideo.R
import com.bg.itunesvideo.data.callback.VideoCallback
import com.bg.itunesvideo.data.model.objects.VideoObj
import com.bg.itunesvideo.data.utils.VideoUtils
import com.bg.itunesvideo.data.view.home.VideoListFragment
import com.bg.itunesvideo.data.view.videoHistory.HistoryFragment
import com.bg.itunesvideo.databinding.VideoListItemBinding
import java.util.ArrayList

/**
 * Created by Balaji Gaikwad on 31/1/21.
 */
class VideoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    private var videoList: ArrayList<VideoObj> = ArrayList<VideoObj>()
    private var callback: VideoCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mSongListItemBinding = DataBindingUtil.inflate<VideoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.video_list_item, parent, false
        )
        return SongListViewHolder(mSongListItemBinding)

    }

    override fun onBindViewHolder(videoViewHolder: RecyclerView.ViewHolder, position: Int) {

        val listItem = videoList[position]

        val headerViewHolder = videoViewHolder as SongListViewHolder

        videoViewHolder.videoListItemBinding.videoModel = listItem
        videoViewHolder.videoListItemBinding.videoUtils=VideoUtils

        videoViewHolder.videoListItemBinding.rootLayout.setOnClickListener {
            callback?.onClick(listItem)
        }


    }


    override fun getItemCount(): Int {
        return if (videoList != null) {
            videoList!!.size
        } else {
            0
        }
    }

    /** setMusicList method is use to initialise Video list
     * screen list data
     * */
    fun setMusicList(
        context1: VideoListFragment,

        musicListModel: List<VideoObj>
    ) {
        videoList=musicListModel as ArrayList<VideoObj>
        this.callback=context1
        notifyDataSetChanged()
    }


    /** setMusicList method is use to initialise history
     * screen list data
     * */
    fun setMusicList(
        context1: HistoryFragment,

        musicListModel: List<VideoObj>
    ) {
        videoList=musicListModel as ArrayList<VideoObj>
        this.callback=context1
        notifyDataSetChanged()
    }

    class SongListViewHolder(var videoListItemBinding: VideoListItemBinding) :
        RecyclerView.ViewHolder(videoListItemBinding.root)

}