package com.christopher.elias.base.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/

@Parcelize
data class TodoModel(val id: Int,
                val userId: String,
                val title: String,
                val completed: Boolean) : Parcelable