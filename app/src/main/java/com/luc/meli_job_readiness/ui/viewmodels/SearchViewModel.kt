package com.luc.meli_job_readiness.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.data.repositories.ProductDataSource
import com.luc.meli_job_readiness.domain.ProductRepository

class SearchViewModel constructor(private val productRepository: ProductRepository) : ViewModel() {

    fun getCategory(category: String) = liveData {
        val data = productRepository.getCategory(category)
        if (data is NetworkResponse.Success)
            emit(data.data)
    }

    fun getItems(categoryId: String) = liveData {
        val data = productRepository.getItems(categoryId)
        if (data is NetworkResponse.Success)
            emit(data.data)
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ProductRepository(ProductDataSource())) as T
    }
}