package com.christopher.elias.data.network.response

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
data class TodoResponse(val id: Int,
                        val userId: Int,
                        val title: String,
                        val completed: Boolean)