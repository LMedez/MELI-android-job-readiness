package com.luc.meli_job_readiness.domain

import com.luc.meli_job_readiness.data.service.LocalStorageService

class SearchRepository constructor(private val localStorageService: LocalStorageService) {

    fun getUserSearch() = localStorageService.getUserSearch()

    fun saveUserSearch(query: String) = localStorageService.saveUserSearch(query)
}