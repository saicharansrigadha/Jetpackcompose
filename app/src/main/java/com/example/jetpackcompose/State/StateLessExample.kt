package com.example.jetpackcompose.State

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class StateLessExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data=intent.getIntExtra("value",0)

        setContent {
            var value by remember { mutableStateOf(0) }
            StateLess(
                value = value,
                onIncrement = { value++ }, // Correct increment
                data?:0
            )
        }

    }
}

@Composable
fun StateLess(value: Int, onIncrement: () -> Unit, data: Int?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onIncrement()
                }
            ) {
                Text(
                    text = "Count: $value",
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = data?.toString() ?: "",
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowPreview() {
    StateLess(0, onIncrement = {}, 0)
}
