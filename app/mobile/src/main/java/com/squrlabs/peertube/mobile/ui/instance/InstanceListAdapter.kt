package com.squrlabs.peertube.mobile.ui.instance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.mobile.R
import com.squrlabs.peertube.util.SingleLiveEvent
import kotlinx.android.synthetic.main.instance_list_item.view.*


class InstanceListAdapter(
    private val diffUtil: InstanceListDiffUtil,
    private val context: Context
) : ListAdapter<InstanceModel, InstanceListViewHolder>(diffUtil) {

    val selectedInstance: LiveData<InstanceModel>
        get() = _selectedInstance

    private val _selectedInstance = SingleLiveEvent<InstanceModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstanceListViewHolder {
        return InstanceListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.instance_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InstanceListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            _selectedInstance.value = currentItem
        }
    }
}

class InstanceListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = view.txtName!!
    private val description = view.txtDescription!!
    private val host = view.txtHost!!
    fun bind(model: InstanceModel) {
        name.text = model.name!!
        description.text = model.shortDescription?: ""
        host.text = model.host?: ""
    }
}

class InstanceListDiffUtil : DiffUtil.ItemCallback<InstanceModel>() {
    override fun areItemsTheSame(oldItem: InstanceModel, newItem: InstanceModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem:InstanceModel, newItem: InstanceModel): Boolean {
        return oldItem.id == newItem.id
    }
}