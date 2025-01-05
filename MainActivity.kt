package com.example.booknest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booknest.ui.theme.BookNestTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookNestTheme {
                val navController = rememberNavController()
                val mainViewModel = viewModel<MainViewModel>()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController, mainViewModel) }
                    composable("addBook") { AddBookScreen(navController, mainViewModel) }
                    composable("bookDetails/{bookId}") { backStackEntry ->
                        val bookId = backStackEntry.arguments?.getString("bookId")
                        if (bookId != null) {
                            BookDetailsScreen(bookId, navController, mainViewModel)
                        }
                    }
                    composable("editBook/{bookId}") { backStackEntry ->
                        val bookId = backStackEntry.arguments?.getString("bookId")
                        if (bookId != null) {
                            EditBookScreen(bookId, navController, mainViewModel)
                        }
                    }
                }

            }
        }
    }
}
