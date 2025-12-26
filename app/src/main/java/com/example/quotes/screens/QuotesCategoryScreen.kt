package com.example.quotes.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotes.R
import com.example.quotes.viewmodels.QuotesCategoryViewModel


@Composable
fun QuoteCategoryScreen() {
    val quotesCategoryViewModel: QuotesCategoryViewModel = viewModel()
    val quoteCategoryList = quotesCategoryViewModel.quotesCategory.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(quoteCategoryList.value.distinct()) {
            QuoteCategoryItem(it)
        }
    }

}

@Composable
fun QuoteCategoryItem(category: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
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