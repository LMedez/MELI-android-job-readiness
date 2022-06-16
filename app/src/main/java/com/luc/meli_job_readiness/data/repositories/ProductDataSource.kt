package com.luc.meli_job_readiness.data.repositories

import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.retrofit.RetrofitService

class ProductDataSource {
    private val retrofitInstance = RetrofitService.instance
    suspend fun getCategory(category: String): NetworkResponse<String> {
        return try {
            val response = retrofitInstance.getCategory(search = category)
            NetworkResponse.Success(response.body()?.first()?.categoryId ?: "")
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> {
        return try {
            val response = retrofitInstance.getItems(categoryId)
            NetworkResponse.Success(response.body()?.content ?: listOf())
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

    suspend fun getProducts(ids: List<String>): NetworkResponse<List<DataModel.Product>> {
        return try {
            val response = retrofitInstance.getProducts(ids.joinToString(","))
            val data = response.body()?.map { it.body } ?: listOf()
            data[0].title?.let {
                return NetworkResponse.Success(response.body()?.map { it.body } ?: listOf())
            }
            NetworkResponse.Error(null, "The fields of Product are null")
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }
}