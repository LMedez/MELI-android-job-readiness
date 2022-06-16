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
        val shipping: Shipping,
        val pictures: List<Picture>,
        val condition: String,
        val variations: List<Variation>,
        @SerializedName("sold_quantity") val soldQuantity: Double,
        @SerializedName("seller_address") val sellerAddress: SellerAddress
    )

    data class Shipping(
        @SerializedName("free_shipping")
        val freeShipping: Boolean = false
    )

    data class Variation(@SerializedName("attribute_combinations") val attribute: List<Attribute>)

    data class Attribute(val id: String, @SerializedName("value_name") val valueName: String)

    data class Picture(val id: String, @SerializedName("secure_url") val secureUrl: String)

    data class SearchLocation(val city: Location, val state: Location)

    data class SellerAddress(@SerializedName("search_location") val searchLocation: SearchLocation)

    data class Location(val name: String)


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
