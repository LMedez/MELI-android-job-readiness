package com.luc.meli_job_readiness.data.retrofit

import com.luc.meli_job_readiness.BuildConfig
import com.luc.meli_job_readiness.data.repositories.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request =
                chain.request().newBuilder().header("Authorization", "Bearer ${BuildConfig.API_KEY}").build()
            chain.proceed(request)
        }
    }.build()

    val instance: ProductService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProductService::class.java)
}