package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.data.repositories.ProductDataSource

class ProductRepository constructor(private val productDataSource: ProductDataSource) {

    suspend fun getCategory(category: String): NetworkResponse<String> =
        productDataSource.getCategory(category)

    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> =
        productDataSource.getItems(categoryId)
}