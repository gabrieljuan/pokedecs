package com.azure.pokedecs.navigation

import android.net.Uri
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.azure.feature.detail.PokeDetailRoute
import com.azure.feature.home.HomeRoute
import com.azure.feature.login.LoginRoute
import com.azure.feature.register.RegisterRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        composable(Routes.LOGIN) {
            LoginRoute(
                onBackClick = { navController.popBackStack() },
                onLoginSuccess = { username ->
                    navController.navigate(Routes.Home.createRoute(username)) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }
        composable(Routes.REGISTER) {
            RegisterRoute(
                onBackClick = { navController.popBackStack() },
                onRegisterSuccess = { username ->
                    navController.navigate(Routes.Home.createRoute(username)) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
            )
        }
        composable(
            route = Routes.Home.ROUTE,
            arguments = listOf(
                navArgument(Routes.Home.ARGS_USERNAME) { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val username = navBackStackEntry.arguments?.getString(Routes.Home.ARGS_USERNAME) ?: return@composable
            HomeRoute(
                username = username
            ) { pokeName ->
                navController.navigate("detail/${Uri.encode(pokeName)}")
            }
        }
        composable(
            route = Routes.PokeDetail.ROUTE,
            arguments = listOf(
                navArgument(Routes.PokeDetail.ARGS_NAME) { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val pokeName = navBackStackEntry.arguments?.getString(Routes.PokeDetail.ARGS_NAME) ?: return@composable
            PokeDetailRoute(pokeName = pokeName) {
                navController.popBackStack()
            }
        }
    }
}