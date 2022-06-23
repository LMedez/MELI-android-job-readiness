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
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT = "PRODUCT"
    }

    // Create an instance of the SearchViewModel and pass the factory as parameter
    private val searchViewModel: SearchViewModel by viewModels(factoryProducer = { SearchViewModelFactory(this) })

    // ViewBinding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ProductItemAdapter()

        // Create an Intent to start activity ProductDetailActivity with a Product object in extras
        adapter.setItemClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT, it)
            }
            startActivity(intent, bundle)
        }

        searchViewModel.category.postValue("notebook")

        // Observe the product list and set the adapter with the list
        searchViewModel.productList.observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.itemRV.adapter = adapter.apply { productList = it }
        }

        // Observe the status of loading data and show a progress bar
        searchViewModel.loadingData.observe(this) {
            adapter.productList = listOf()
            binding.progressBar.visibility = View.VISIBLE
        }

        // Observe the status of the error message and show a SnackBar if exists
        searchViewModel.showError.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).apply {
                anchorView = binding.bottomNavigation
                show()
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        // Show the Search fragment on click in the SearchView
        binding.searchCV.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.root.id, SearchFragment())
                addToBackStack(SearchFragment().tag)
            }
        }
    }
}