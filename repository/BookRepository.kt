package com.example.booknest.repository

import com.example.booknest.data.Book
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Book] from a given data source.
 */
interface BookRepository {
    /**
     * Retrieve all the Books from the the given data source.
     */
    fun getAllBooksStream(): Flow<List<Book>>

    /**
     * Retrieve an Book from the given data source that matches with the [id].
     */
    fun getBookStream(id: Int): Flow<Book?>

    /**
     * Insert Book in the data source
     */
    suspend fun insertBook(Book: Book)

    /**
     * Delete Book from the data source
     */
    suspend fun deleteBook(Book: Book)

    /**
     * Update Book in the data source
     */
    suspend fun updateBook(Book: Book)
}
