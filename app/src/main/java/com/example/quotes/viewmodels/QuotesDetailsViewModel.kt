package com.example.quotes.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import com.example.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Quotes Details screen.
 * 
 * This ViewModel manages the state and business logic for displaying
 * quotes of a specific category. It retrieves the category from navigation
 * arguments and fetches the corresponding quotes from the repository.
 * 
 * Uses Hilt for dependency injection and SavedStateHandle to get navigation arguments.
 */
@HiltViewModel
class QuotesDetailsViewModel @Inject constructor(
    private val repository: QuotesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Exposes the list of quotes for the selected category as a StateFlow.
     * 
     * The UI can observe this StateFlow to reactively update when quotes are loaded.
     * Returns the singleCategoryQuotes StateFlow from the repository.
     */
    val quotes: StateFlow<List<QuotesItem>>
        get() = repository.singleCategoryQuotes

    /**
     * Initialization block that fetches quotes for the selected category.
     * 
     * Retrieves the category name from SavedStateHandle (passed via navigation),
     * defaults to "Life" if not provided, and then fetches quotes for that category.
     * Uses viewModelScope to launch a coroutine that will be automatically cancelled
     * when the ViewModel is cleared.
     */
    init {
        viewModelScope.launch {
            // Get category from navigation arguments, default to "Life" if not found
            val category = savedStateHandle.get<String>("category") ?: "Life"
            println("ZACT  $category")
            repository.getSingleCategoryQuotes(category)
        }
    }
}