package com.luc.meli_job_readiness.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.data.repositories.ProductDataSource
import com.luc.meli_job_readiness.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel constructor(private val productRepository: ProductRepository) : ViewModel() {


    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> = _showError

    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean> = _loadingData

    val category = MutableLiveData<String>()

    val productList = Transformations.switchMap(category) {
        getProducts(it)
    }

    private fun getProducts(category: String) = liveData {
        _loadingData.postValue(true)
        val result = productRepository.getCategory(category)
        if (result is NetworkResponse.Success) {
            val items = productRepository.getItems(result.data)
            if (items is NetworkResponse.Success) {
                val products = productRepository.getProducts(items.data.map { it.id })
                if (products is NetworkResponse.Success) {
                    emit(products.data)
                    return@liveData
                }
            }
        }
        _showError.postValue("An unexpected error occurred")
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ProductRepository(ProductDataSource())) as T
    }
}