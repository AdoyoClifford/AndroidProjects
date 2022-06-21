package com.adyo.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adyo.restaurant.ui.theme.RestaurantScreen
import com.adyo.restaurant.ui.theme.RestaurantTheme

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
            RestaurantScreen{id ->
            navController.navigate("restaurants/$id")}
        }
        composable(route = "restaurants/{restaurant_id}") {
            RestaurantDetailsScreen()
        }
    }
}

