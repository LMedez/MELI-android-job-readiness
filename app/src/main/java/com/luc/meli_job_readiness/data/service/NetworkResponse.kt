package com.luc.meli_job_readiness.data.service

sealed class NetworkResponse <out T> {
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error<out T>(val exception: Exception?, val errorMessage: String) : NetworkResponse<T>()
}