package com.christopher.elias.base.ui.bindingtools

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

@BindingAdapter("setCustomErrorMessage")
fun setCustomErrorMessage(tv: TextView, message: Any?) {
    tv.text = when(message) {
        is String -> message
        is Int -> tv.resources.getString(message)
        else -> ""
    }
}