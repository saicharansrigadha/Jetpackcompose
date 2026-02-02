package com.example.jetpackcompose.Navigator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*

@Composable
fun Setting(counter: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Top bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//               // painter = painterResource(id=R.dra),
//                contentDescription = null,
//                modifier = Modifier.size(150.dp),
//                contentScale = ContentScale.Crop
//            )

            Text(text = "Navigation", color = Color.White)
        }

        // Space between
        Spacer(modifier = Modifier.height(16.dp))

        // Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Navigation with arguments",
                Modifier.padding(10.dp),
                color = Color.Black
            )

            Text(
                text = "Settings Screen, passed data is $counter",
                Modifier.padding(10.dp),
                color = Color.Black
            )
        }
    }
}
