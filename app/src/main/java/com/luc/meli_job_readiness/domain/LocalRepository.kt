package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.service.LocalStorageService

class LocalRepository constructor(private val localStorageService: LocalStorageService) {

    /**
     * Get a list of user queries from Shared Preferences
     */
    fun getUserSearch() = localStorageService.getUserSearch()

    /**
     * Save the user searchable queries in Shared Preferences
     */
    fun saveUserSearch(query: String) = localStorageService.saveUserSearch(query)

    /**
     * Save the user favorite product id in Shared Preferences
     */
    fun saveFavoriteProduct(productId: String) = localStorageService.saveUserFavoriteProduct(productId)

    /**
     * Get the user user favorite product id from Shared Preferences
     */
    fun getFavoriteProducts() = localStorageService.getFavoriteProducts()
}