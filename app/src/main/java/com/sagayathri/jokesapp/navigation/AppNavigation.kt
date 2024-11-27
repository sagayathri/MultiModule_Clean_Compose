package com.sagayathri.jokesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagayathri.presentation.jokeDetail.JokeDetailScreen
import com.sagayathri.presentation.jokeList.JokeListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.JokeList.route) {
        composable(route = NavigationScreen.JokeList.route) {
            JokeListScreen(navController = navController)
        }
        composable(
            route = NavigationScreen.JokeItemByID.route,
            arguments = listOf(navArgument(JOKE_ID) {
                defaultValue = 0
                type = NavType.IntType
            })
        ) {  navBackStackEntry ->
            val jokeId = navBackStackEntry.arguments?.getInt(JOKE_ID)
            jokeId?.let {
                JokeDetailScreen(
                    navController = navController,
                    jokeId = jokeId,
                )
            }
        }
    }
}