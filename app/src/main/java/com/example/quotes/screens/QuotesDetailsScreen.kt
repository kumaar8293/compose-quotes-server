package com.example.quotes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotes.models.QuotesItem
import com.example.quotes.viewmodels.QuotesDetailsViewModel

/**
 * Composable screen that displays quotes for a selected category.
 * 
 * This screen shows a scrollable list of quotes filtered by the category
 * passed via navigation arguments. The category is extracted by the ViewModel
 * from SavedStateHandle and used to fetch the appropriate quotes.
 */
@Composable
fun QuoteDetailsScreen() {
    // Get ViewModel instance using Hilt
    val quoteDetailsViewModel : QuotesDetailsViewModel = hiltViewModel()
    // Observe the quotes StateFlow and convert to State for Compose
    val quotes = quoteDetailsViewModel.quotes.collectAsState()

    // Display quotes in a scrollable column
    LazyColumn() {
        items(quotes.value){
            QuoteListItem(it)
        }
    }

}

/**
 * Composable item that displays a single quote in a card.
 * 
 * Each quote is displayed in a Material3 Card with the quote text.
 * The card has a light gray border and padding for better visual separation.
 * 
 * @param quotes The QuotesItem containing the quote text and metadata
 */
@Composable
fun QuoteListItem(quotes: QuotesItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(
            text = quotes.text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }

}