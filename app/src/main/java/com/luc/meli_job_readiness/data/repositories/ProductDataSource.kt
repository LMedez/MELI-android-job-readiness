package com.luc.meli_job_readiness.data.repositories

import android.util.Log
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.retrofit.RetrofitService

class ProductDataSource {
    suspend fun getCategory(category: String): NetworkResponse<String> {
        return try {
            val response = RetrofitService.instance.getCategory(search = category)
            NetworkResponse.Success(response.body()?.first()?.categoryId ?: "")
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> {
        return try {
            val response = RetrofitService.instance.getItems(categoryId)
            NetworkResponse.Success(response.body()?.content ?: listOf())
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

}