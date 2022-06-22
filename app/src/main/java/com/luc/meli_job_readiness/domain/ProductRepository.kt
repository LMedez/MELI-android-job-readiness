package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.service.NetworkResponse
import com.luc.meli_job_readiness.data.service.ProductServiceImpl

class ProductRepository constructor(private val productServiceImpl: ProductServiceImpl) {

    /**
     * Get the category id
     */
    suspend fun getCategory(category: String): NetworkResponse<String> =
        productServiceImpl.getCategory(category)

    /**
     * Get a list of items
     */
    suspend fun getItems(categoryId: String): NetworkResponse<List<DataModel.Item>> =
        productServiceImpl.getItems(categoryId)

    /**
     * Get a list of Products
     */
    suspend fun getProducts(ids: List<String>): NetworkResponse<List<DataModel.Product>> =
        productServiceImpl.getProducts(ids)

    suspend fun getProductDescription(id: String): NetworkResponse<String> =
        productServiceImpl.getProductDescription(id)

}