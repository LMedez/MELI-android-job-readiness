package com.luc.meli_job_readiness.model

import com.google.gson.annotations.SerializedName

data class Product(
    val title: String = "",
    val price: Int = 0,
    val thumbnail: String = "",
    
    @SerializedName("free_shipping")
    val freeShipping: Boolean = false
)
