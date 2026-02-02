package com.example.jetpackcompose.Navigator

sealed class Routes(val route:String){
    object Home:Routes("Home")
    object Profile :Routes("Profile")
    object Settings:Routes("Settings")

}