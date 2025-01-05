package com.example.booknest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BookDetailsScreen(bookId: Int, navController: NavController, viewModel: MainViewModel) {
    val book = viewModel.fetchBookDetails(bookId).getOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        Spacer(modifier = Modifier.height(16.dp)) // Spacer after the back button

        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }

        book?.let {
            // Book Details
            Text(text = it.title, fontSize = 24.sp, color = Color.Black)
            Text(text = "Author: ${it.author}", fontSize = 18.sp, color = Color.Black)
            Text(text = it.description, fontSize = 16.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp)) // Spacer between details and buttons

            // Delete Button
            Button(
                onClick = {
                    viewModel.deleteBook(bookId)
                    navController.popBackStack() // Navigate back after deletion
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Book")
            }

            // Edit Button
            Button(
                onClick = {
                    navController.navigate("editBook/$bookId") // Navigate to edit screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Book")
            }
        } ?: Text("Book not found", color = Color.Red) // Display error message if book is not found
    }
}
