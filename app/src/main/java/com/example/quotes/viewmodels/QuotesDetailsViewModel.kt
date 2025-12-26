package com.example.quotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import com.example.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesDetailsViewModel @Inject constructor(private val repository: QuotesRepository) : ViewModel() {

    val quotes: StateFlow<List<QuotesItem>>
        get() = repository.singleCategoryQuotes

    init {
        viewModelScope.launch {
          repository.getSingleCategoryQuotes("Life")
        }
    }
}