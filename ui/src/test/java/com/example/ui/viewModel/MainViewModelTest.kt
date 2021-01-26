package com.example.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.impl.UserRepositoryImpl
import com.example.domain.model.Result
import com.example.domain.usecases.GetSingleUserUseCase
import com.example.domain.usecases.GetUserListUseCase
import com.example.ui.core.MainCoroutineRule
import com.example.ui.mapper.UserModelMapper
import com.example.ui.model.UserModel
import com.example.ui.screens.LatestUiState
import com.example.ui.screens.MainViewModel
import com.example.ui.viewModel.DataFactory.makeUserListEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var sut: MainViewModel

    @Mock
    lateinit var getUserListUseCase: GetUserListUseCase

    @Mock
    lateinit var getSingleUserUseCase: GetSingleUserUseCase

    @Mock
    private lateinit var mapperModel: UserModelMapper

    @Mock
    private lateinit var repository: UserRepositoryImpl

    @Mock
    private lateinit var observer: Observer<LatestUiState<List<UserModel>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<LatestUiState<List<UserModel>>>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        sut = MainViewModel(
            getUserListUseCase,
            getSingleUserUseCase, mapperModel
        )
    }

    @Test
    fun `loading state is observed first when fetch users list`() = runBlockingTest {

        //Given
        sut.usersResult.observeForever(observer)
        `when`(repository.getUserList()).thenReturn(
            flow {
                emit(Result(makeUserListEntity(2)))
            }
        )

        //When
        sut.getUsersList()
        //then
        verify(observer).onChanged(captor.capture())
        assertEquals(captor.value, LatestUiState.Loading)

    }


}