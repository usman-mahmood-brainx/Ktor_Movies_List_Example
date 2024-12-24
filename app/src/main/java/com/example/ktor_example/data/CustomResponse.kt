package com.example.ktor_example.data.network

sealed class CustomResponse<out T> {
    data class Success<out T>(val data: T) : CustomResponse<T>()
    data class Failure(val exception: Throwable) : CustomResponse<Nothing>()
    object Loading : CustomResponse<Nothing>()
}