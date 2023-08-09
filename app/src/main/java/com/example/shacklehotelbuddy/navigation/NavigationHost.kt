package com.example.shacklehotelbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shacklehotelbuddy.navigation.Screens.Companion.SEARCH_SCREEN_ARG
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.main.MainScreen
import com.example.shacklehotelbuddy.ui.screen.main.MainViewModel
import com.example.shacklehotelbuddy.ui.screen.search.SearchResultScreen
import com.example.shacklehotelbuddy.ui.screen.search.SearchViewModel
import com.google.gson.Gson

sealed class Screens(val route: String) {
    companion object {
        const val SEARCH_SCREEN_ARG = "booking"
        const val SEARCH_SCREEN_ARG_BODY = "{booking}"

        const val MAIN_SCREEN_ROUTE = "main_screen"
        const val SEARCH_SCREEN_ROUTE = "search_screen"
    }

    object MainScreen : Screens(route = MAIN_SCREEN_ROUTE)
    object SearchScreen :
        Screens(route = "$SEARCH_SCREEN_ROUTE/$SEARCH_SCREEN_ARG=$SEARCH_SCREEN_ARG_BODY")
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {

        composable(route = Screens.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()

            MainScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(route = Screens.SearchScreen.route) { backStackEntry ->
            val viewModel = hiltViewModel<SearchViewModel>()
            val bookingJson = backStackEntry.arguments?.getString(SEARCH_SCREEN_ARG)
            val bookingItem = Gson().fromJson(bookingJson, BookingItem::class.java)

            SearchResultScreen(
                viewModel = viewModel,
                navController = navController,
                searchQuery = bookingItem
            )
        }
    }
}
