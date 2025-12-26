package com.example.quotes.models

/**
 * Data class representing a single quote item.
 * 
 * This model represents the structure of a quote object from the API.
 * Each quote contains information about the author, category, category image,
 * and the quote text itself.
 * 
 * @param author The name of the quote author
 * @param category The category this quote belongs to (e.g., "Life", "Success")
 * @param categoryImage URL or path to the category image
 * @param text The actual quote text
 */
data class QuotesItem(
    val author: String,
    val category: String,
    val categoryImage: String,
    val text: String
)