package com.example.jetpackcompose.Navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.*

class NavActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent{
            ScreenMain()
        }
    }
}
@Composable
fun ScreenMain(){
   val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        //Home

        composable(
            Routes.Home.route
        ) {
            Home(navController = navController)
        }
        // Profile
        composable(Routes.Profile.route) {
            Profile()
        }
        // Settings
        // "/{id}" - its the argument passed down from homeScreen
        composable(Routes.Settings.route + "/{id}") { navBackStack ->
            // Extracting the argument
            val counter = navBackStack.arguments?.getString("id")
            Setting(counter = counter)
        }
    }
}