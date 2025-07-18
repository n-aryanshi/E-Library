package com.example.e_library.domain.repo

import com.example.e_library.common.Book
import com.example.e_library.common.BookCategory
import com.example.e_library.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {

    //interact with data class
    //interact with the allBookRepoImpl class
    //interact with the viewmodel

    fun getAllBooks(): Flow<ResultState<List<Book>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategory>>>
    fun getAllBooksByCategory(category: String): Flow<ResultState<List<Book>>>
}