package com.christopher.elias.domain.entity

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
data class TodoEntity(val id: Int,
                      val userId: Int,
                      val title: String,
                      val completed: Boolean)