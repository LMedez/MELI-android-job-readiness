package com.luc.meli_job_readiness.data.repositories

import com.luc.meli_job_readiness.data.retrofit.RetrofitService

class ProductDataSource {
    suspend fun getCategory(category: String): NetworkResponse<String> {
        return try {
            val response = RetrofitService.instance.getCategory(search = category)
            NetworkResponse.Success(response.body()?.first()?.categoryId ?: "")
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message?: "An unexpected error occurred")
        }
    }

}