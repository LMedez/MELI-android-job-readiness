package com.luc.meli_job_readiness.data.model

import com.google.gson.annotations.SerializedName

object DataModel {
    /*
    * Product
    *
    * */

    data class ProductBody(val body: Product)

    data class Product(
        val id: String,
        val title: String,
        val price: Double,
        val thumbnail: String,
        val shipping: Shipping
    )

    data class Shipping(
        @SerializedName("free_shipping")
        val freeShipping: Boolean = false
    )

    /*
    * Category
    *
    * */

    data class Category(
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("category_name")
        val categoryName: String
    )

    /*
    * Items
    *
    * */

    data class ListItems(val content: List<Item>)

    data class Item(val id: String, val position: Int)

}
