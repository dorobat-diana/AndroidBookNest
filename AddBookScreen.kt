package com.example.booknest

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddBookScreen(navController: NavController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Add a New Book", fontSize = 20.sp)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Placeholder for image upload action
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Photo")
        }

        Button(
            onClick = {
                // Validation for empty fields
                if (title.isBlank() || author.isBlank()) {
                    errorMessage = "Title and Author cannot be empty."
                } else if (viewModel.isDuplicateBook(title, author)) {
                    // Check for duplicate book
                    errorMessage = "This book already exists."
                } else {
                    // Add book if all validations pass
                    viewModel.addBook(title, author, description)
                    navController.popBackStack() // Navigate back after adding the book
                    errorMessage = null // Clear error message after successful addition
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Book")
        }

        errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
