package com.example.quotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Quotes app.
 * 
 * This class is annotated with @HiltAndroidApp to enable Hilt dependency injection
 * throughout the application. Hilt will generate the necessary code for dependency
 * injection setup when the app starts.
 * 
 * Make sure to register this class in AndroidManifest.xml with the name attribute.
 */
@HiltAndroidApp
class QuotesApplication : Application() {
}