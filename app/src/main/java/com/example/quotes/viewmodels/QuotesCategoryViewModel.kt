package com.example.quotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesCategoryViewModel @Inject constructor(private val repository: QuotesRepository) :
    ViewModel() {

    val quotesCategory: StateFlow<List<String>>
        get() = repository.quotesCategory

    init {
        viewModelScope.launch {
            repository.getQuotesCategory()
        }
    }

}