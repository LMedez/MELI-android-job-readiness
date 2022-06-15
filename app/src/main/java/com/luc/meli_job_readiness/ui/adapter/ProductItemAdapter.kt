package com.luc.meli_job_readiness.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.databinding.ProductItemBinding

class ProductItemAdapter(private val productList: List<DataModel.Product>) :
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProductItemBinding.bind(view)
        fun bind(product: DataModel.Product) = with(binding) {
            binding.imageUrl = product.thumbnail
            binding.itemTitleTV.text = product.title
            binding.itemPriceTV.text = product.price.toString()
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