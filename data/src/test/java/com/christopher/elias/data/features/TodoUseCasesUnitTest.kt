package com.christopher.elias.data.features

import com.christopher.elias.data.base.BaseUseCaseUniTest
import com.christopher.elias.domain.usecase.GetTodoUseCase
import com.christopher.elias.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

/**
 * Created by Christopher Elias on 27/01/2020.
 * celias@peruapps.com.pe
 *
 * Peru Apps
 * Lima, Peru.
 **/
class TodoUseCasesUnitTest : BaseUseCaseUniTest() {

    private val getTodoUseCase by inject<GetTodoUseCase>()
    private val getTodosListUseCase by inject<GetTodosUseCase>()

    @Test
    fun `GET TODO LIST`() = runBlocking {
        getTodosListUseCase.invoke(this, "") {
            it.either(::printUseCaseFailure, ::printUseCaseSuccessList)
        }
    }

    @Test
    fun `GET TODO object of id 1`() = runBlocking {
        val params = GetTodoUseCase.Params(1)
        getTodoUseCase.invoke(this, params) {
            it.either(::printUseCaseFailure, ::printUseCaseSuccessObject)
        }
    }
}