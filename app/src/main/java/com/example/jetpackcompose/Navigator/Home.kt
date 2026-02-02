package com.example.jetpackcompose.Navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    // Basic counter to display on screen
    var counter by remember {
        mutableIntStateOf(0)
    }
    // Box to center Items
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {
            // Text to show counter on Screen
            Text(text = "Home, Counter is $counter", color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            // Button increases the counter
            Button(onClick = { counter++ }) {
                Text(text = "Increment Counter", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Navigate to Profile Screen
            Button(onClick = {
                navController.navigate(Routes.Profile.route)
            }) {
                Text(text = "Navigate to Profile", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Navigate to Settings Screen
            Button(onClick = {
                navController.navigate(Routes.Settings.route + "/$counter")
            }) {
                Text(text = "Navigate to Settings", color = Color.White)
            }
        }
    }
}