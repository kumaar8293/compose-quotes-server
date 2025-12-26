package com.example.quotes.models

/**
 * Data model representing a collection of quotes.
 * 
 * This class extends ArrayList<QuotesItem> and is used to represent
 * the response from the API when fetching all quotes.
 * It's essentially a type alias for a list of QuotesItem objects.
 */
class Quotes : ArrayList<QuotesItem>()