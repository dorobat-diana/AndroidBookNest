package com.example.booknest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.Companion.ABORT)
    suspend fun insertBook(book: Book)

    @Query("""
    UPDATE book 
    SET 
        title = :title, 
        author = :author, 
        description = :description 
    WHERE bookId = :bookId
""")
    suspend fun updateBookFieldsById(
        bookId: Int,
        title: String,
        author: String,
        description: String
    )


    @Query("DELETE FROM book WHERE bookId = :bookId")
    suspend fun deleteBook(bookId: Int)

    @Query("Select * FROM book")
    fun getBooks() : Flow<List<Book>>

    @Query("SELECT EXISTS(SELECT 1 FROM book WHERE title = :title AND author = :author AND bookId != :bookId)")
    fun isDuplicateBook(title: String, author: String, bookId: Int): Boolean

    @Query("SELECT * FROM book WHERE bookId = :bookId")
    fun getBookById(bookId: Int): Flow<Book>

    @Delete
    fun delete(book: Book)

    @Update
    fun update(book: Book)
}