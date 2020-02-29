package com.christopher.elias.domain.di

import com.christopher.elias.domain.usecase.GetTodoUseCase
import com.christopher.elias.domain.usecase.GetTodosUseCase
import org.koin.dsl.module

/**
 * Created by Christopher Elias on 27/01/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/


val useCasesModule = module {
    factory { GetTodoUseCase(get()) }
    factory { GetTodosUseCase(get()) }
}