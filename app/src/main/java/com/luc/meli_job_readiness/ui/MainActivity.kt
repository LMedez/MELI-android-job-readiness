package com.luc.meli_job_readiness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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

        searchViewModel.category.postValue("samsung")

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

    }
}