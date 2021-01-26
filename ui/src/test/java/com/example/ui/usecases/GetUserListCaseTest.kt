package com.example.ui.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.model.Result
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.GetUserListUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUserListCaseTest {

    private lateinit var sut: GetUserListUseCase

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    @Mock
    lateinit var repository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = GetUserListUseCase(repository, postExecutorThread)
    }


    @Test
    fun `ensures getUserList is called`() {
        whenever(
            repository.getUserList()
        ).thenReturn(
            flow {
                emit(
                    Result(users = emptyList())
                )
            }
        )

        sut.execute()
        verify(repository).getUserList()
    }
}