package com.example.booknest.data

import android.content.Context
import com.example.booknest.repository.BookRepository
import com.example.booknest.repository.OfflineBookRepository

interface AppContainer {
    val itemsRepository: BookRepository
}

class AppDataContainer(private val context: Context) {

    val bookRepository: BookRepository by lazy {
        OfflineBookRepository(BookDatabase.getDatabase(context).dao())
    }
}
