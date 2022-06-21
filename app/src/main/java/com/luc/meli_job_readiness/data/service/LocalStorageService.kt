package com.luc.meli_job_readiness.data.service

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Class to storage in local the user queries
 */
class LocalStorageService(context: Context) {
    companion object {
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES"
        private const val USER_SEARCH = "USER_SEARCH"
    }

    private val storage = context.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    /**
     * Save user searchable queries into mutableSet in Shared Preferences
     */
    fun saveUserSearch(query: String) {
        val searchList = storage.getStringSet(USER_SEARCH, mutableSetOf())
        searchList?.add(query)
        with(storage.edit()) {
            remove(USER_SEARCH).apply()
            putStringSet(USER_SEARCH, searchList).apply()
        }
    }

    /**
     * Get a mutable set of user queries from Shared Preferences
     */
    fun getUserSearch(): MutableSet<String>? = storage.getStringSet(USER_SEARCH, mutableSetOf())
}