package com.example.domain.usecases

import com.example.domain.executor.PostExecutorThread
import com.example.domain.model.User
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: UserRepository,
    private val postExecution: PostExecutorThread
) : FlowUseCase<Unit, List<User>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecution.io

    override fun execute(params: Unit?): Flow<List<User>> {
        return repository.getUserList()
    }

}