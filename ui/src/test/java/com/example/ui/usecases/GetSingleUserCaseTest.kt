package com.example.ui.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.model.User
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.GetSingleUserUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetSingleUserCaseTest {

    private lateinit var sut: GetSingleUserUseCase

    @Mock
    lateinit var postExecutorThread: PostExecutorThread

    @Mock
    lateinit var repository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = GetSingleUserUseCase(repository, postExecutorThread)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `execute throws error when params is null`() {
        sut.execute()
    }

    @Test
    fun `ensures getUser is called`() {
        whenever(
            repository.getAUser("1")
        ).thenReturn(
            flow {
                emit(
                    User(
                        randomBuild(),
                        randomBuild(),
                        randomBuild(),
                        randomBuild(),
                        randomBuild(), randomBuild()
                    )
                )
            }
        )

        sut.execute("1")
        verify(repository).getAUser("1")
    }
}