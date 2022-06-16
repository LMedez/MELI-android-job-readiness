package com.luc.meli_job_readiness.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.databinding.ProductItemBinding
import com.luc.meli_job_readiness.ui.animation.startFavAnimation

class ProductItemAdapter(private val productList: List<DataModel.Product>) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)
        fun bind(product: DataModel.Product) = with(binding) {
            imageUrl = product.thumbnail
            itemTitleTV.text = product.title
            itemPriceTV.text = "$ ${product.price.toInt()}"
            favIB.setOnClickListener {
                favIB.startFavAnimation()
            }
            if (product.shipping.freeShipping)
                shippingTV.visibility = View.VISIBLE
            else shippingTV.visibility = View.INVISIBLE
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

}