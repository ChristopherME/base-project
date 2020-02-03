package com.christopher.elias.base.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Christopher Elias on 3/02/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

@Parcelize
data class TodoModel(val id: Int,
                val userId: String,
                val title: String,
                val completed: Boolean) : Parcelable