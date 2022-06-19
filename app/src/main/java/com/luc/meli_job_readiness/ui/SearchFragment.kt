package com.luc.meli_job_readiness.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.transition.Fade
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialSharedAxis
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.databinding.FragmentSearchBinding
import com.luc.meli_job_readiness.ui.viewmodels.SearchViewModel

class SearchFragment : Fragment() {

    // ViewBinding for this fragments views.
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // Instance of SearchViewModel witch is the same as MainActivity ViewModel instance.
    private val searchViewModel: SearchViewModel by activityViewModels()

    /**
     * Returns the root view from the ViewBinding on create the fragment view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up enter and return transition animation for this fragment
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Y, /* forward= */ false)

        // Event for arrow back button that returns to a MainActivity
        binding.arrowBackIB.setOnClickListener {
            popBackStack()
        }

        // Set the category in the ViewModel with the query from the search view and return to MainActivity
        binding.searchSV.onSubmit {
            if (checkQuery(it)) {
                searchViewModel.category.postValue(it)
                popBackStack()
            } else Snackbar.make(binding.root, getString(R.string.query_check), Snackbar.LENGTH_SHORT).show()
        }

        // Show the keyboard just enter this Fragment
        binding.searchSV.showKeyboard()
    }

    /**
     * Extension function that handle the submit method of de edit text
     * Params: a lambda function with the edit text query as a parameter
     */
    private fun EditText.onSubmit(func: (String) -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                func(text.toString())
            }
            true
        }
    }

    /**
     * Check if query not contains special character, doesn't start with white space
     * and doesn't contains only numbers
     * Params: the query to analyze
     * Returns: true if doesn't match, otherwise false
     */
    fun checkQuery(query: String) = !query.matches("[0-9]+".toRegex())
            && query.matches("[a-zA-Z0-9 ]*".toRegex())
            && !query.startsWith(" ")
            && !query.endsWith(" ")


    /**
     * Extension function that show the keyboard and set the current focus to the edit text
     */
    private fun EditText.showKeyboard() {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Come back to the MainActivity
     */
    private fun popBackStack() {
        parentFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    /**
     * Sets to null the ViewBinding instance on destroy the fragment view
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}