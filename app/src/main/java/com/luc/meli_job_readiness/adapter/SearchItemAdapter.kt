package com.luc.meli_job_readiness.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.databinding.SearchItemBinding
import com.luc.meli_job_readiness.model.Product

class SearchItemAdapter(private val productList: List<Product>) : RecyclerView.Adapter<SearchItemAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SearchItemBinding.bind(view)
        fun bind(product: Product) = with(binding) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size
}