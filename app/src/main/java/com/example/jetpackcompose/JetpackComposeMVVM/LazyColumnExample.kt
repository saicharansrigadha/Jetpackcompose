package com.example.jetpackcompose.JetpackComposeMVVM

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LazyColumnExample : AppCompatActivity() {

    // var data: ArrayList<Model>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = arrayListOf(
            Model("Saicharan"),
            Model("Vishnu")
        )
        setContent {
            Example(
                this@LazyColumnExample,
                data

            )
        }
    }
}


@Composable
fun Example(context: LazyColumnExample, list: List<Model>?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(list?.size ?: 0) { item ->
                Cell(
                    name = list?.get(item)?.title,
                )
            }
        }


    }

}

@Composable
fun Cell(name: String?) {
    Text(
        text = name ?: "",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                //onClick()
            }
    )
}

@Preview
@Composable
fun Show() {
    Example(LazyColumnExample(),list = null)
}