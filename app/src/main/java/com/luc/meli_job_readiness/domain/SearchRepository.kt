package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.service.LocalStorageService

class SearchRepository constructor(private val localStorageService: LocalStorageService) {

    /**
     * Get a list of user queries from Shared Preferences
     */
    fun getUserSearch() = localStorageService.getUserSearch()

    /**
     * Save the user searchable queries in Shared Preferences
     */
    fun saveUserSearch(query: String) = localStorageService.saveUserSearch(query)
}