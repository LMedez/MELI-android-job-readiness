package com.luc.meli_job_readiness.data.repositories

import com.luc.meli_job_readiness.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("sites/{country_id}/domain_discovery/search")
    suspend fun getCategory(
        @Path("country_id") siteId: String = "MLM",
        @Query("limit") limit: Int = 1,
        @Query("q") search: String
    ): Response<List<DataModel.Category>>
}