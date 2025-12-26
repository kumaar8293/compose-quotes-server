package com.example.quotes.di

import com.example.quotes.api.QuotesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt Dagger Module for providing network-related dependencies.
 * 
 * This module configures Retrofit, OkHttp, and the logging interceptor.
 * All dependencies provided here are singletons and available throughout the app
 * via Hilt dependency injection.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Provides an HttpLoggingInterceptor instance for logging HTTP requests and responses.
     * 
     * The interceptor is configured with BODY level logging, which logs:
     * - Request headers and body
     * - Response headers and body
     * 
     * This is useful for debugging API calls during development.
     * 
     * @return Configured HttpLoggingInterceptor instance
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides an OkHttpClient instance with logging interceptor attached.
     * 
     * OkHttpClient is the underlying HTTP client used by Retrofit.
     * The logging interceptor is added to log all network requests and responses.
     * 
     * @param loggingInterceptor The logging interceptor to add to the client
     * @return Configured OkHttpClient instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    /**
     * Provides a Retrofit instance configured with base URL and Gson converter.
     * 
     * Retrofit is used to create type-safe HTTP clients from API interfaces.
     * This instance is configured to:
     * - Use JSONBin.io as the base URL
     * - Use the provided OkHttpClient (with logging)
     * - Use Gson for JSON serialization/deserialization
     * 
     * @param okHttpClient The OkHttpClient instance to use for HTTP calls
     * @return Configured Retrofit instance
     */
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the QuotesApi implementation.
     * 
     * Creates an implementation of the QuotesApi interface using Retrofit.
     * This is the main API interface used throughout the app for fetching quotes data.
     * 
     * @param retrofit The Retrofit instance to create the API from
     * @return QuotesApi implementation
     */
    @Singleton
    @Provides
    fun provideQuotesApi(retrofit: Retrofit): QuotesApi {
        return retrofit.create(QuotesApi::class.java)
    }

}