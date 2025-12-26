package com.example.quotes.api

import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * Retrofit API interface for fetching quotes data from JSONBin.io API.
 * 
 * This interface defines all the API endpoints used in the app.
 * Retrofit uses these annotations to generate the HTTP client implementation.
 */
interface QuotesApi {
    /**
     * Fetches all quotes from the API.
     * 
     * @return Response containing a Quotes object (list of all quotes)
     */
    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    suspend fun getQuotes(): Response<Quotes>

    /**
     * Fetches quotes filtered by a specific category using JSONPath.
     * 
     * Uses the X-JSON-Path header to filter quotes by category dynamically.
     * The category parameter should be a JSONPath expression like:
     * "quotes[?(@.category==\"Life\")]"
     * 
     * @param category JSONPath expression to filter quotes by category
     * @return Response containing a list of QuotesItem for the specified category
     */
    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    suspend fun getSingleCategoryQuotes(@Header("X-JSON-Path") category: String): Response<List<QuotesItem>>


    /**
     * Fetches the list of all unique quote categories.
     * 
     * This endpoint uses a fixed JSONPath header to extract only the category field
     * from all quotes. Useful for populating the category selection screen.
     * 
     * @return Response containing a list of category names (strings)
     */
    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    @Headers("X-JSON-Path: quotes..category")
    suspend fun getQuotesCategoryList(): Response<List<String>>
}