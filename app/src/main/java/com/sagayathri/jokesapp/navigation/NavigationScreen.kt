package com.sagayathri.jokesapp.navigation

internal const val JOKE_ID = "JOKE_ID"

sealed class NavigationScreen(val route: String) {
    data object JokeList : NavigationScreen(route = "jokes")
    data object JokeItemByID : NavigationScreen(route = "jokes/{$JOKE_ID}")
}
