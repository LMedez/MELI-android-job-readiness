package com.luc.meli_job_readiness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.luc.meli_job_readiness.databinding.ActivityMainBinding
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModel
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModels(factoryProducer = { SearchViewModelFactory() })
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        searchViewModel.getCategory("samsung").observe(this) {
            Log.d("sometag", "onCreate: $it")
        }

    }
}