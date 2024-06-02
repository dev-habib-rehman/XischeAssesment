package com.example.modulea.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.modulea.utils.adpaterUtils.GenericRecyclerItem

class GenericRecyclerAdapter(
    private val context: Context?,
    private val itemClickHandler: ((Any) -> Unit)?,
) : RecyclerView.Adapter<BindingViewHolder>() {

    private val items = mutableListOf<GenericRecyclerItem>()
    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = getItem(position).layoutId
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder, position: Int
    ) {
        getItem(position).bind(
            holder.binding, context = context, itemClickHandler
        )
        holder.binding.executePendingBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<GenericRecyclerItem>, isClearPreviousData: Boolean = true) {
        if (isClearPreviousData) this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = items[position]

}

class BindingViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)