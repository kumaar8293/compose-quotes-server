package com.example.quotes.repository

import com.example.quotes.api.QuotesApi
import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val quotesApi: QuotesApi) {
    private val _quotes = MutableStateFlow(Quotes())
    val quotes = _quotes.asStateFlow()

    private val _quotesCategory = MutableStateFlow<List<String>>(emptyList())
    val quotesCategory = _quotesCategory.asStateFlow()

    private val _singleCategoryQuotes = MutableStateFlow<List<QuotesItem>>(emptyList())
    val singleCategoryQuotes = _singleCategoryQuotes.asStateFlow()


    suspend fun getQuotesList() {
        val response = quotesApi.getQuotes()
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _quotes.value = response.body()!!
        }
    }


    suspend fun getSingleCategoryQuotes(category: String) {
        val response = quotesApi.getSingleCategoryQuotes("quotes[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _singleCategoryQuotes.value = response.body()!!
        }
    }


    suspend fun getQuotesCategory() {
        val response = quotesApi.getQuotesCategoryList()
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _quotesCategory.value = response.body()!!
        }
    }
}