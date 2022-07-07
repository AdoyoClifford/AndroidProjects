package com.adoyo.restaurant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.adoyo.restaurant.presentation.details.RestaurantDetailsScreen
import com.adoyo.restaurant.presentation.lists.RestaurantsViewModel
import com.adoyo.restaurant.presentation.lists.RestaurantScreen
import com.adoyo.restaurant.ui.theme.RestaurantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantTheme {
                RestaurantApp()
            }
        }
    }
}

@Composable
fun RestaurantApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "restaurants") {
        composable(route = "restaurants"){
            val viewModel: RestaurantsViewModel = viewModel()
            RestaurantScreen(state = viewModel.state.value,
            onItemClick = { id ->
                navController.navigate("restaurants/$id")
            },
            onFavoriteClick = {id,oldValue ->
                viewModel.toggleFavorite(id, oldValue)
            })
        }
        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id"){
            type = NavType.IntType
        }),
            deepLinks = listOf(navDeepLink { uriPattern =
                "www.restaurantsapp.details.com/{restaurant_id}" }
        )) {
            RestaurantDetailsScreen()
        }
    }
}



