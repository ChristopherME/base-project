package com.christopher.elias.data.di

import com.christopher.elias.data.network.mapper.TodoMapper
import com.christopher.elias.data.network.mapper.TodoMapperImpl
import org.koin.dsl.module
/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/

val mapperModule = module {
    single<TodoMapper>{ TodoMapperImpl() }
}
