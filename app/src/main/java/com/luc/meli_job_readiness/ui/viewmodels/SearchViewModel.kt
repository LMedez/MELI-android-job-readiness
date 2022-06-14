package com.luc.meli_job_readiness.ui.viewmodels

import androidx.lifecycle.*
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.data.repositories.NetworkResponse
import com.luc.meli_job_readiness.data.repositories.ProductDataSource
import com.luc.meli_job_readiness.domain.ProductRepository
import kotlinx.coroutines.launch

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
            if (items is NetworkResponse.Success)
                emit(productRepository.getProducts(items.data.map { it.id }))
            else _showError.postValue("An unexpected error occurred calling api")
        } else _showError.postValue("An unexpected error occurred calling api")
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ProductRepository(ProductDataSource())) as T
    }
}