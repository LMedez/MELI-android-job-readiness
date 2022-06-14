package com.luc.meli_job_readiness.data.model

import com.google.gson.annotations.SerializedName

object DataModel {

    data class Product(
        val id: String,
        val title: String,
        val price: Int,
        val thumbnail: String,


        @SerializedName("free_shipping")
        val freeShipping: Boolean = false
    )

    data class Category(
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("category_name")
        val categoryName: String
    )
}
