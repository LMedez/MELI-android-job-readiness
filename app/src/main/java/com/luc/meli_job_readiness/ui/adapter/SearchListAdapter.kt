package com.luc.meli_job_readiness.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.databinding.SearchItemBinding

/**
 * Adapter for the list view in SearchFragment
 * Params: the search list from the local source, a lambda function for the on click event of anchor button
 */
class SearchListAdapter(
    context: Context,
    private val searchList: List<String>,
    private val onAnchorClick: (String) -> Unit,
    private val onItemClick: (String) -> Unit
) : ArrayAdapter<String>(context, R.layout.search_item, searchList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            SearchItemBinding.inflate(LayoutInflater.from(context), null, false)
        binding.searchTV.text = searchList[position]
        binding.anchorBT.setOnClickListener { onAnchorClick(searchList[position]) }
        binding.root.setOnClickListener { onItemClick(searchList[position]) }
        return binding.root
    }
}
