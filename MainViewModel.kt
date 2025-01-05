package com.example.booknest

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booknest.data.Book
import com.example.booknest.data.BookDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class MainViewModel(private val dao: BookDao) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    init {
        state.items = dao.getBooks() as LiveData<List<Book>>
    }

    fun fetchBookDetails(bookId: Int): Result<Flow<Book>> {
        return try {
            val book = dao.getBookById(bookId)
            if (book != null) {
                Result.success(book)
            } else {
                Result.failure(NoSuchElementException("Book with ID $bookId not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun addBook(title: String, author: String, description: String) {
        // Validation for non-blank fields
//        if (title.isBlank() || author.isBlank() || description.isBlank()) {
//            Log.e("ERROR", "Fields cannot be blank")
//            return
//        }
//
//        // Check for duplicate
//        if (isDuplicateBook(title, author)) {
//            Log.e("ERROR", "Book already exists")
//            return
//        }
//
//        // Add new book
//        val newBook = Book(
//            bookId = UUID.randomUUID().toString(),
//            title = title,
//            author = author,
//            description = description
//        )
//        state.items.add(newBook)
    }

    suspend fun deleteBook(bookId: Int) {
        dao.deleteBook(bookId)
    }

    suspend fun updateBook(bookId: Int, title: String, author: String, description: String) {
        // Validation for non-blank fields
        if (title.isBlank() || author.isBlank() || description.isBlank()) {
            Log.e("ERROR", "Fields cannot be blank")
            return
        }

        // Check for duplicates in updated values
        if (isDuplicateBook(title, author, bookId)) {
            Log.e("ERROR", "Updating would cause a duplicate")
            return
        }

        // Find and update the book
        dao.updateBookFieldsById(bookId, title, author, description)
    }

    fun isDuplicateBook(title: String, author: String, bookId: Int): Boolean {
        return dao.isDuplicateBook(title, author, bookId)
    }

}

data class ScreenState(
    var items: LiveData<List<Book>> = MutableLiveData(emptyList())
)
