package com.example.quotes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotes.R
import com.example.quotes.viewmodels.QuotesCategoryViewModel

/**
 * Composable screen that displays a grid of quote categories.
 * 
 * This screen shows all available quote categories in a 2-column grid layout.
 * When a category is clicked, it navigates to the details screen showing quotes
 * for that category.
 * 
 * @param onClick Lambda function called when a category is clicked, receives the category name
 */
@Composable
fun QuoteCategoryScreen(onClick: (category: String) -> Unit) {
    // Get ViewModel instance using Hilt
    val quotesCategoryViewModel: QuotesCategoryViewModel = hiltViewModel()
    // Observe the categories StateFlow and convert to State for Compose
    val quoteCategoryList = quotesCategoryViewModel.quotesCategory.collectAsState()

    // Display categories in a 2-column grid
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // Display each unique category as a clickable item
        items(quoteCategoryList.value.distinct()) {
            QuoteCategoryItem(it, onClick)
        }
    }

}

/**
 * Composable item that displays a single category card.
 * 
 * Each category is displayed as a clickable box with a background image
 * and the category name at the bottom. When clicked, it triggers navigation
 * to show quotes for that category.
 * 
 * @param category The category name to display
 * @param onClick Lambda function called when the category card is clicked
 */
@Composable
fun QuoteCategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                // Navigate to details screen when clicked
                onClick(category)
            }
            .size(180.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(R.drawable.bg), contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(0.dp, 20.dp)
        )
    }
}