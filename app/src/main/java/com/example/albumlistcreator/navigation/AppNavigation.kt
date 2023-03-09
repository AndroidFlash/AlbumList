package com.example.albumlistcreator.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.albumlistcreator.feature.main.view.AlbumSearchComponent

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AlbumSearch.route
    ) {
        composable(
            route = Screen.AlbumSearch.route
        ) {
            AlbumSearchComponent( viewModel = hiltViewModel() )
        }
    }
}

sealed class Screen(val route: String) {
    object AlbumSearch: Screen("search")
//    object AlbumDetails: Screen("details/")
}
