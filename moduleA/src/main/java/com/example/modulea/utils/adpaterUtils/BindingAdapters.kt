package com.example.modulea.utils.adpaterUtils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.University
import com.example.modulea.BR
import com.example.modulea.R
import com.example.modulea.adapters.GenericRecyclerAdapter


@BindingAdapter("items", "itemClickHandler", requireAll = false)
fun setRecyclerViewItems(
    recyclerView: RecyclerView,
    items: List<GenericRecyclerItem>?,
    itemClickHandler: ((Any) -> Unit)?,
) {
    var adapter = (recyclerView.adapter as? GenericRecyclerAdapter)
    if (adapter == null) {
        adapter = GenericRecyclerAdapter(recyclerView.context, itemClickHandler)
        recyclerView.adapter = adapter
    }
    adapter.updateData(items.orEmpty())
}

fun University.toRecyclerItem() = GenericRecyclerItem(
    data = this, variableId = BR.universityItem, layoutId = R.layout.item_university
)