package com.example.quotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Quotes Category screen.
 * 
 * This ViewModel manages the state and business logic for displaying
 * the list of quote categories. It fetches categories from the repository
 * when initialized and exposes them through a StateFlow for the UI to observe.
 * 
 * Uses Hilt for dependency injection and follows MVVM architecture pattern.
 */
@HiltViewModel
class QuotesCategoryViewModel @Inject constructor(private val repository: QuotesRepository) :
    ViewModel() {

    /**
     * Exposes the list of quote categories as a StateFlow.
     * 
     * The UI can observe this StateFlow to reactively update when categories are loaded.
     * Returns the categories StateFlow from the repository.
     */
    val quotesCategory: StateFlow<List<String>>
        get() = repository.quotesCategory

    /**
     * Initialization block that fetches quote categories when the ViewModel is created.
     * 
     * Uses viewModelScope to launch a coroutine that will be automatically cancelled
     * when the ViewModel is cleared, preventing memory leaks.
     */
    init {
        viewModelScope.launch {
            repository.getQuotesCategory()
        }
    }

}