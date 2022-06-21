package com.luc.meli_job_readiness.data.source

import com.luc.meli_job_readiness.BuildConfig
import com.luc.meli_job_readiness.data.service.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    /**
     * Add an interceptor in the Retrofit instance to add an authorization header with the token in all API query
     */
    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request =
                chain.request().newBuilder().header("Authorization", "Bearer ${BuildConfig.API_KEY}").build()
            chain.proceed(request)
        }
    }.build()

    /**
     * Create an instance of Retrofit
     */
    val instance: ProductService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProductService::class.java)
}