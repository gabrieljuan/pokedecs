package com.azure.pokedecs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.azure.feature.home.HomeRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
    ) {
        composable(Routes.LOGIN) {

        }
        composable(Routes.HOME) {
            HomeRoute {  }
        }
        composable(route = "detail/{name}") {

        }
    }
}