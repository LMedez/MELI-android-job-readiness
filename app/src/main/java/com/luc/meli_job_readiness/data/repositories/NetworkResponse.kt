package com.luc.meli_job_readiness.data.repositories

sealed class NetworkResponse <out T> {
    class Loading<out T> : NetworkResponse<T>()
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error<out T>(val exception: Exception?, val errorMessage: String) : NetworkResponse<T>()
}