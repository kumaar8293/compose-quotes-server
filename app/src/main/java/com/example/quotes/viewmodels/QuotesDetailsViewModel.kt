package com.example.quotes.viewmodels

import androidx.lifecycle.ViewModel
import com.example.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesDetailsViewModel @Inject constructor(private val repository: QuotesRepository) : ViewModel() {


}