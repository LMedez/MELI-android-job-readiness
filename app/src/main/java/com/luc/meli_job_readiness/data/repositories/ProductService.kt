package com.luc.meli_job_readiness.data.repositories

import com.luc.meli_job_readiness.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("sites/MLM/domain_discovery/search")
    suspend fun getCategory(
        @Query("limit") limit: Int = 1,
        @Query("q") search: String
    ): Response<List<DataModel.Category>>

    @GET("highlights/MLM/category/{category_id}")
    suspend fun getItems(@Path("category_id") categoryId: String): Response<DataModel.ListItems>

    @GET("items")
    suspend fun getProducts(@Query(value = "ids", encoded = true) ids: String): Response<List<DataModel.ProductBody>>
}