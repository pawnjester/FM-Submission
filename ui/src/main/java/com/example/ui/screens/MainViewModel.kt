package com.example.ui.screens

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetSingleUserUseCase
import com.example.domain.usecases.GetUserListUseCase
import com.example.ui.mapper.UserModelMapper
import com.example.ui.model.UserModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getUserList: GetUserListUseCase,
    private val getAUser: GetSingleUserUseCase,
    private val mapper: UserModelMapper
) : ViewModel() {


    private val _users = MutableLiveData<LatestUiState<List<UserModel>>>()
    var usersResult: LiveData<LatestUiState<List<UserModel>>> = _users

    private val _user = MutableLiveData<LatestUiState<UserModel>>()
    var userResult: LiveData<LatestUiState<UserModel>> = _user

    var idOfUser: String? = null

    fun setUserDetailId(id: String) {
        idOfUser = id
    }


    fun getUsersList() {
        viewModelScope.launch {
            getUserList()
                .map { result ->
                    val error: Throwable? = result.error
                    if (error == null) {
                        val users = result.users
                        if (users.isNotEmpty()) {
                            LatestUiState.Success(mapper.mapToModelList(result.users))
                        } else {
                            LatestUiState.Empty
                        }
                    } else {
                        if (result.users.isEmpty()) {
                            LatestUiState.Error(error.message ?: "")
                        } else {
                            LatestUiState.Success(mapper.mapToModelList(result.users))
                        }
                    }
                }.onStart {
                    _users.value = LatestUiState.Loading
                }
                .collect {
                    _users.value = it
                }
        }
    }

    fun getASingleUser() {
        _user.value = LatestUiState.Loading
        viewModelScope.launch {
            getAUser(idOfUser).map {
                mapper.mapToModel(it)
            }.catch {
                _user.value = LatestUiState.Error("Cannot retrieve users")
            }.collect {
                _user.value = LatestUiState.Success(it)
            }
        }
    }
}

sealed class LatestUiState<out T : Any> {
    data class Success<out T : Any>(val users: T) : LatestUiState<T>()
    object Loading : LatestUiState<Nothing>()
    object Empty : LatestUiState<Nothing>()
    data class Error(val exception: String) : LatestUiState<Nothing>()
}