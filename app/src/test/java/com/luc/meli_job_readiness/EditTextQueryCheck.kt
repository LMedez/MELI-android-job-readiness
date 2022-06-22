package com.luc.meli_job_readiness

import com.luc.meli_job_readiness.ui.SearchFragment
import org.junit.Test

import org.junit.Assert.*

/**
 * Test the checkQuery function of SearchFragment
 */
class EditTextQueryCheck {

    private val fragmentSearch = SearchFragment()

    @Test
    fun `query contains only letters and numbers, returns true`() {
        val query = "Samsung j7"
        val result = fragmentSearch.checkQuery(query)
        assertEquals(result,true)
    }

    @Test
    fun `query only with numbers, returns false`() {
        val query = "45456"
        val result = fragmentSearch.checkQuery(query)
        assertEquals(result,false)
    }

    @Test
    fun `query with special character, returns false`() {
        val query = "samsung ?=!"
        val result = fragmentSearch.checkQuery(query)
        assertEquals(result,false)
    }

    @Test
    fun `query start with a white space, return false`() {
        val query = " books"
        val result = fragmentSearch.checkQuery(query)
        assertEquals(result,false)
    }

    @Test
    fun `query ends with white space, return false`() {
        val query = "Notebooks "
        val result = fragmentSearch.checkQuery(query)
        assertEquals(result,false)
    }
}