package com.example.e_library.presentation.navigation

import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable //-> serialize to JSON
    object HomeScreen

    @Serializable
    data class BooksByCategory(val category: String)
    //Use data class only when you're passing structured data as arguments.

    @Serializable
    data class ShowPdfScreen(val url: String)

}