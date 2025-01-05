package com.example.booknest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EditBookScreen(bookId: Int, navController: NavController, viewModel: MainViewModel) {
    val book = viewModel.fetchBookDetails(bookId).getOrNull()
    var title by remember { mutableStateOf(book?.title ?: "") }
    var author by remember { mutableStateOf(book?.author ?: "") }
    var description by remember { mutableStateOf(book?.description ?: "") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Edit Book Details", fontSize = 20.sp)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null && title.isBlank()
        )
        if (title.isBlank() && errorMessage != null) {
            Text(text = "Title cannot be empty", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null && author.isBlank()
        )
        if (author.isBlank() && errorMessage != null) {
            Text(text = "Author cannot be empty", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage != null && errorMessage == "duplicate") {
            Text(text = "This book already exists.", color = Color.Red, fontSize = 12.sp)
        }

        Button(
            onClick = {
                if (title.isBlank() || author.isBlank()) {
                    errorMessage = "emptyFields"
                } else if (viewModel.isDuplicateBook(title, author) && (title != book?.title || author != book?.author)) {
                    errorMessage = "duplicate"
                } else {
                    viewModel.updateBook(bookId, title, author, description)
                    navController.popBackStack() // Go back after updating
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}
