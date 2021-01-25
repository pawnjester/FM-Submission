package com.example.domain.model

data class Result(val users: List<User>, val error: Throwable? = null)