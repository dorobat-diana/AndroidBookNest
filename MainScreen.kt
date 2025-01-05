package com.example.booknest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp), // Add top padding to avoid overlap with the status bar
        contentPadding = PaddingValues(bottom = 16.dp) // Bottom padding for AddBookButton visibility
    ) {
        items(state.items.size()) { i ->
            val item = state.items[i]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navController.navigate("bookDetails/${item.bookId}") } // Navigate on click
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = "Cover for ${item.title}",
                    modifier = Modifier.size(64.dp) // Make the icon smaller
                )
                Spacer(modifier = Modifier.width(16.dp)) // Space between icon and text
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f) // Allow column to take available width
                ) {
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = item.author,
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        AddBookButton(navController)
    }
}
