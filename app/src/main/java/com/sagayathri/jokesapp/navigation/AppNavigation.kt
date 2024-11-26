package com.sagayathri.jokesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.JokeList.route) {
        composable(route = NavigationScreen.JokeList.route) {
//            CatListScreen(navController = navController)
        }
        composable(
            route = NavigationScreen.JokePunchLine.route,
            arguments = listOf(navArgument(JOKE_ID) {
                type = NavType.StringType
            })
        ) {
//            CatDetailsScreen(navController = navController)
        }
    }
}