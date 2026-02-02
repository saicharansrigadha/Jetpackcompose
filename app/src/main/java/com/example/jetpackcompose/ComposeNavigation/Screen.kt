package com.example.jetpackcompose.ComposeNavigation

sealed class Screen (val route: String){
    object Home:Screen("Home_Screen")
    object Details:Screen("Details_Screen")
}