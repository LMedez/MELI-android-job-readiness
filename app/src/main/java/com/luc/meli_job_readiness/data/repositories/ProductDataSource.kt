package com.luc.meli_job_readiness.data.repositories

import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.retrofit.RetrofitService

class ProductDataSource {

    // Getting instance of retrofit
    private val retrofitInstance = RetrofitService.instance

    /**
     * Get the id of the first category by the query.
     * Params: the query search
     * Returns: a NetworkStatus.Success object with the id, or a NetworkStatus.Error in case of any exception
     */
    suspend fun getCategory(query: String): NetworkResponse<String> {
        return try {
            val response = retrofitInstance.getCategory(query = query)
            NetworkResponse.Success(response.body()?.first()?.categoryId ?: "")
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

    /**
     * Get a list of Item by category.
     * Params: the category id
     * Returns a NetworkStatus.Success object with a list of Item, or a NetworkStatus.Error in case of any exception
     */
    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> {
        return try {
            val response = retrofitInstance.getItems(categoryId)
            NetworkResponse.Success(response.body()?.content ?: listOf())
        } catch (e: Exception) {
            NetworkResponse.Error(e, e.message ?: "An unexpected error occurred")
        }
    }

    /**
     * Get a list of Product
     * Params: list of String with the Item id
     * Returns a NetworkStatus.Success object with a list of Product, or a NetworkStatus.Error in case of any exception
     */
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