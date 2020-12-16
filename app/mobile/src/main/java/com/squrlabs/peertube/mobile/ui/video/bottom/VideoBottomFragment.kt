package com.squrlabs.peertube.mobile.ui.video.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squrlabs.peertube.mobile.R
import com.tuanhav95.drag.utils.inflate
import kotlinx.android.synthetic.main.base_feed_item.view.*
import kotlinx.android.synthetic.main.video_bottom_fragment.*

class VideoBottomFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.video_bottom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.adapter = ListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }


    class ListAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(parent.inflate(R.layout.base_feed_item))
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Glide.with(holder.itemView.ivThumbnail)
                .load("https://via.placeholder.com/500.jpg")
                .into(holder.itemView.ivThumbnail)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}