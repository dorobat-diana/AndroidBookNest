package com.example.booknest.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.booknest.R

@Entity
data class Book(
    var title: String,
    var author: String,
    var description: String,
    val status: Status? = null,
    val userRating: Float? = null,
    val overallRating: Float? = null,
    val numberOfVotes: Int? = 0,
    val review: String? = null,
    @DrawableRes val imageRes: Int = R.drawable.baseline_menu_book_24,
    @PrimaryKey(autoGenerate = true)
    val bookId: Int = 0
) {
    enum class Status {
        TO_READ, READING, FINISHED
    }
}
