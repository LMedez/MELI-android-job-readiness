package com.luc.meli_job_readiness.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import com.luc.meli_job_readiness.databinding.ActivityMainBinding
import com.luc.meli_job_readiness.ui.adapter.ProductItemAdapter
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModel
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModels(factoryProducer = { SearchViewModelFactory() })

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ProductItemAdapter()

        adapter.setItemClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("product", it)
            }
            startActivity(intent, bundle)
        }

        searchViewModel.category.postValue("notebook")

        searchViewModel.productList.observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.itemRV.adapter = adapter.apply { productList = it }
        }

        searchViewModel.loadingData.observe(this) {
            binding.progressBar.visibility = View.VISIBLE
        }

        searchViewModel.showError.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.INVISIBLE
        }

        binding.searchCV.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.root.id, SearchFragment())
                addToBackStack(SearchFragment().tag)
            }
        }
    }
}