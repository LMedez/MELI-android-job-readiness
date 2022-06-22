package com.luc.meli_job_readiness.data.service

import com.luc.meli_job_readiness.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    /**
     * Get a list of Category by the query from API.
     * Params: the limit of Categories : the query search.
     * Returns a Retrofit Response with a list of Categories.
     */
    @GET("sites/MLA/domain_discovery/search")
    suspend fun getCategory(
        @Query("limit") limit: Int = 1,
        @Query("q") query: String
    ): Response<List<DataModel.Category>>

    /**
     * Get a list of Items by Category id from API.
     * Params: the category id
     * Returns a Retrofit Response with a ListItems object.
     */
    @GET("highlights/MLA/category/{category_id}")
    suspend fun getItems(@Path("category_id") categoryId: String): Response<DataModel.ListItems>

    /**
     * Get a list of products from API.
     * Params: the list of items ids as String
     * Returns a Retrofit Response with a list of ProductBody objects.
     */
    @GET("items")
    suspend fun getProducts(@Query(value = "ids", encoded = true) ids: String): Response<List<DataModel.ProductBody>>

    @GET("items")
    suspend fun getProductDescription(@Query(value = "ids", encoded = true) itemIds: String): Response<List<DataModel.ProductDescription>>
}