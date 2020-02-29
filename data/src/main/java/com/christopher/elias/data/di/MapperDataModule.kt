package com.christopher.elias.data.di

import com.christopher.elias.data.network.mapper.TodoMapper
import com.christopher.elias.data.network.mapper.TodoMapperImpl
import org.koin.dsl.module
/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/

val mapperDataModule = module {
    single<TodoMapper>{ TodoMapperImpl() }
}
