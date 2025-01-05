package com.example.booknest.repository

import com.example.booknest.data.Book
import com.example.booknest.data.BookDao
import kotlinx.coroutines.flow.Flow

class OfflineBookRepository(private val bookDao: BookDao) : BookRepository {
    override fun getAllBooksStream(): Flow<List<Book>> = bookDao.getBooks()

    override fun getBookStream(id: Int): Flow<Book?> = bookDao.getBookById(id)

    override suspend fun insertBook(Book: Book) = bookDao.insertBook(Book)

    override suspend fun deleteBook(Book: Book) = bookDao.delete(Book)

    override suspend fun updateBook(Book: Book) = bookDao.update(Book)

    fun isDuplicateBook(title: String, author: String, bookId: Int): Boolean {
        return bookDao.isDuplicateBook(title, author, bookId)
    }
}