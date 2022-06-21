package com.luc.meli_job_readiness.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.databinding.ProductItemBinding
import com.luc.meli_job_readiness.ui.animation.startFavAnimation
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

/**
 * Adapter for the RecyclerView with the Product item view
 */
class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    // The list of Products
    var productList: List<DataModel.Product> = listOf()

    // A lambda function that is invoked for the onClickListener event of the root view
    private var onItemClickListener: ((DataModel.Product) -> Unit)? = null

    /**
     * Handle the click event with a provided lambda function
     * Params: a lambda function with the Product selected as parameter
     */
    fun setItemClickListener(listener: (DataModel.Product) -> Unit) {
        onItemClickListener = listener
    }

    /**
     * ViewHolder as inner class to bind the view with de Product object
     * Use ViewBinding for the views
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)

        fun bind(product: DataModel.Product) = with(binding) {
            imageUrl = product.thumbnail
            itemTitleTV.text = product.title
            itemPriceTV.text = "$ ${product.price.toCurrencyPrice()}"
            itemLocationTV.text = product.productLocation
            itemDescriptionTV.text = product.colorVariation.ifEmpty { "" }

            // Handle onClickListener method of the root view and fire the lambda function
            root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(product)
                }
            }

            // Start the fav animation in ImageButton click listener
            favIB.setOnClickListener {
                favIB.startFavAnimation()
            }

            // Show the free shipping TextView if is free, otherwise hide the TextView
            if (product.shipping.freeShipping)
                shippingTV.visibility = View.VISIBLE
            else shippingTV.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size

    /**
     * Extension function that convert the price in ARS
     */
    private fun Double.toCurrencyPrice():String{
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "ES"))
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("ARS")
        return formatter.format(this.roundToInt()).dropLast(3)
    }
}