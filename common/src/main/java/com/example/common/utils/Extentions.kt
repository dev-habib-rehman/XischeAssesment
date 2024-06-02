package com.example.common.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    context?.let {
        Toast.makeText(it, text, length).show()
    }
}