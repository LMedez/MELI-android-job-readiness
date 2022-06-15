package com.luc.meli_job_readiness.ui

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.commit
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.databinding.ActivityMainBinding
import com.luc.meli_job_readiness.ui.adapter.ProductItemAdapter
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModel
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModelFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModels(factoryProducer = { SearchViewModelFactory() })
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        searchViewModel.category.postValue("intel i7")

        searchViewModel.productList.observe(this) {
            Log.d("tests", "entered")

            binding.itemRV.adapter = ProductItemAdapter(it)
        }

        searchViewModel.data.observe(this) {
            Log.d("tests", it.toString())
        }

        searchViewModel.loadingData.observe(this) {
        }

        searchViewModel.showError.observe(this) {
            Log.d("tests", it)
        }

        binding.searchCV.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.root.id, SearchFragment())
                addToBackStack(SearchFragment().tag)
            }

        }
    }
}