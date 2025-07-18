package com.example.e_library.common

sealed class ResultState<out T> {

    //Success<T>, Error<T>, and Loading are subclasses of result state
    //With sealed class:
    //Only one valid state at a time: either loading, success, or error.

    data class Success<out T>(val data: T): ResultState<T>()
    data class Error<out T> (val exception: Exception) : ResultState<T>()

    object Loading: ResultState<Nothing>()

}

