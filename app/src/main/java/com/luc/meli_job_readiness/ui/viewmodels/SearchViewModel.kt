package com.luc.meli_job_readiness.ui.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.luc.meli_job_readiness.data.service.LocalStorageService
import com.luc.meli_job_readiness.data.service.NetworkResponse
import com.luc.meli_job_readiness.data.service.ProductServiceImpl
import com.luc.meli_job_readiness.domain.ProductRepository
import com.luc.meli_job_readiness.domain.SearchRepository

class SearchViewModel constructor(
    private val productRepository: ProductRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    /**
     * Live data that fire a error message when an error occurs in the Repository calls.
     * Is observed in the MainActivity.
     */
    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> = _showError

    /**
     * Live data that shows a boolean if data is loading from Repository.
     * Is observed in the MainActivity.
     */
    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean> = _loadingData

    /**
     * Mutable live data that is modify by the query of a EditText in the SearchFragment
     */
    val category = MutableLiveData<String>()

    /**
     * Live data that respond to changes on category and emit a new list of Product
     * with the corresponding category id
     */
    val productList = Transformations.switchMap(category) {
        getProducts(it)
    }

    /**
     * Get the products calling the Repository methods
     * Emit a Live data with a list of products or show an error if the case
     */
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
        _showError.postValue("An unexpected error occurred, please try search again")
    }

    /**
     * Get the list of searched items by the user
     * Returns a list of user queries
     */
    fun getUserSearchList() = searchRepository.getUserSearch()?.toList() ?: listOf()

    /**
     * Save the user query search in local
     */
    fun saveUserSearch(query: String) = searchRepository.saveUserSearch(query)
}

/**
 * Factory to create an instance of this ViewModel with a Repository as parameter
 */
class SearchViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            ProductRepository(ProductServiceImpl()),
            SearchRepository(LocalStorageService(context))
        ) as T
    }
}

