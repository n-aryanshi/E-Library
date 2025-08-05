package com.example.e_library.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.e_library.presentation.AllBooksByCategory.BooksByCategoryScreen
import com.example.e_library.presentation.UiComp.PdfViewerScreen
import com.example.e_library.presentation.HomeScreen.HomeScreen

@Composable
fun NavGraph(nhc: NavHostController) {

    NavHost(navController = nhc, startDestination = Routes.HomeScreen) {

        composable<Routes.HomeScreen> {
            Routes.HomeScreen
        }

        composable<Routes.ShowPdfScreen> {
            val data = it.toRoute<Routes.ShowPdfScreen>()

            PdfViewerScreen(pdfUrl = data.url)
        }

        composable<Routes.BooksByCategory> {
            val data = it.toRoute<Routes.BooksByCategory>()
            BooksByCategoryScreen(category = data.category, navHostController = nhc)

        }
    }

}




//NavController - A core class that manages app navigation.

//NavHostController - A Compose-specific subclass of NavController.
//extra features specific to Compose (like navigate, popBackStack, etc.).


//NavHost
//A Composable function that displays the current screen based on NavController's state.
//A container that listens to navigation changes and shows the corresponding screen.