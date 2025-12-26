package com.example.quotes.api

import com.example.quotes.models.Quotes
import com.example.quotes.models.QuotesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface QuotesApi {
    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    suspend fun getQuotes(): Response<Quotes>

    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    suspend fun getSingleCategoryQuotes(@Header("X-JSON-Path") category: String): Response<List<QuotesItem>>


    // This is only for testing, if we have fixed headers
    @GET("v3/b/694e220dd0ea881f4040cd2a?meta=false")
    @Headers("X-JSON-Path: quotes..category")
    suspend fun getQuotesCategoryList(): Response<List<String>>
}