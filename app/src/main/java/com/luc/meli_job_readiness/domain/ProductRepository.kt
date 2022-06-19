package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.data.repositories.ProductDataSource

class ProductRepository constructor(private val productDataSource: ProductDataSource) {

    /**
     * Get the category id
     */
    suspend fun getCategory(category: String): NetworkResponse<String> =
        productDataSource.getCategory(category)

    /**
     * Get a list of items
     */
    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> =
        productDataSource.getItems(categoryId)

    /**
     * Get a list of Products
     */
    suspend fun getProducts(ids: List<String>): NetworkResponse<List<DataModel.Product>> =
        productDataSource.getProducts(ids)
}