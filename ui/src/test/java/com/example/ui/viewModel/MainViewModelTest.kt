package com.example.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecases.GetSingleUserUseCase
import com.example.domain.usecases.GetUserListUseCase
import com.example.ui.core.MainCoroutinesRule
import com.example.ui.mapper.UserModelMapper
import com.example.ui.screens.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var getUserListUseCase: GetUserListUseCase

    @Mock
    private lateinit var getSingleUserUseCase: GetSingleUserUseCase

    @Mock
    private lateinit var mapperModel: UserModelMapper

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel(
            getUserListUseCase,
            getSingleUserUseCase, mapperModel
        )
    }

    @Test
    fun `fetch users list`() {

    }

    @Test
    fun `fetch a single user`() {

    }


}