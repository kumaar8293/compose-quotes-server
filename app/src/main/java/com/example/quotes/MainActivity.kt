package com.example.quotes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quotes.api.QuotesApi
import com.example.quotes.screens.QuoteCategoryScreen
import com.example.quotes.screens.QuoteDetailsScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main Activity for the Quotes application.
 * 
 * This activity serves as the entry point of the app and sets up the Compose UI.
 * It uses Hilt for dependency injection (@AndroidEntryPoint) and Jetpack Compose
 * for the user interface.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Injected QuotesApi instance for making API calls.
     * Provided by Hilt through NetworkModule.
     */
    @Inject
    lateinit var quotesApi: QuotesApi
    
    /**
     * Called when the activity is created.
     * Sets up edge-to-edge display and initializes the Compose UI.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

/**
 * Main composable function that sets up navigation for the app.
 * 
 * This function creates a NavHost with two destinations:
 * 1. "category" - Shows the list of quote categories
 * 2. "details/{category}" - Shows quotes for a specific category
 * 
 * Uses Jetpack Navigation Compose for screen navigation.
 */
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        // Route for displaying the category selection screen
        composable(route = "category") { it: NavBackStackEntry ->
            QuoteCategoryScreen { it: String ->
                // Navigate to details screen when a category is clicked
                navController.navigate("details/$it")
            }
        }

        // Route for displaying quotes of a specific category
        // Takes a category parameter from the navigation route
        composable(
            route = "details/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            QuoteDetailsScreen()
        }
    }

}