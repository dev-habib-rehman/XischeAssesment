package com.example.modulea.utils.adpaterUtils

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.example.common.model.University
import com.example.modulea.databinding.ItemUniversityBinding

data class GenericRecyclerItem(
    val data: Any, @LayoutRes val layoutId: Int, val variableId: Int
) {
    fun bind(
        binding: ViewDataBinding, context: Context? = null, itemClickHandler: ((Any) -> Unit)?
    ) {
        binding.setVariable(variableId, data)
        when (binding) {
            is ItemUniversityBinding -> {
                binding.btnNextUniversityItem.setOnClickListener {
                    itemClickHandler?.invoke(data)
                }
            }
        }
    }
}
