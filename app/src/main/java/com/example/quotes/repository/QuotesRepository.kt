package com.example.quotes.repository

import com.example.quotes.api.QuotesApi
import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Repository class for managing quotes data.
 * 
 * This class acts as a single source of truth for quotes data in the app.
 * It handles API calls and exposes data through StateFlow for reactive UI updates.
 * Follows the Repository pattern to abstract data sources from ViewModels.
 */
class QuotesRepository @Inject constructor(private val quotesApi: QuotesApi) {
    /**
     * Private mutable StateFlow for all quotes.
     * Updated internally, exposed as read-only StateFlow.
     */
    private val _quotes = MutableStateFlow(Quotes())
    /**
     * Public read-only StateFlow for all quotes.
     * ViewModels can observe this to get updates when quotes change.
     */
    val quotes = _quotes.asStateFlow()

    /**
     * Private mutable StateFlow for quote categories list.
     */
    private val _quotesCategory = MutableStateFlow<List<String>>(emptyList())
    /**
     * Public read-only StateFlow for quote categories.
     * Used to populate the category selection screen.
     */
    val quotesCategory = _quotesCategory.asStateFlow()

    /**
     * Private mutable StateFlow for quotes of a single category.
     */
    private val _singleCategoryQuotes = MutableStateFlow<List<QuotesItem>>(emptyList())
    /**
     * Public read-only StateFlow for quotes of a specific category.
     * Used to display quotes when a category is selected.
     */
    val singleCategoryQuotes = _singleCategoryQuotes.asStateFlow()


    /**
     * Fetches all quotes from the API and updates the _quotes StateFlow.
     * 
     * This method makes an API call to get all quotes and updates the internal
     * StateFlow if the response is successful and contains data.
     */
    suspend fun getQuotesList() {
        val response = quotesApi.getQuotes()
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _quotes.value = response.body()!!
        }
    }


    /**
     * Fetches quotes for a specific category and updates the _singleCategoryQuotes StateFlow.
     * 
     * Uses JSONPath filtering to get only quotes matching the specified category.
     * The JSONPath expression filters quotes where category equals the provided category name.
     * 
     * @param category The category name to filter quotes by (e.g., "Life", "Success")
     */
    suspend fun getSingleCategoryQuotes(category: String) {
        val response = quotesApi.getSingleCategoryQuotes("quotes[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _singleCategoryQuotes.value = response.body()!!
        }
    }


    /**
     * Fetches the list of all unique quote categories and updates the _quotesCategory StateFlow.
     * 
     * This method calls the API endpoint that returns only the category field
     * from all quotes, which is used to populate the category selection screen.
     */
    suspend fun getQuotesCategory() {
        val response = quotesApi.getQuotesCategoryList()
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _quotesCategory.value = response.body()!!
        }
    }
}