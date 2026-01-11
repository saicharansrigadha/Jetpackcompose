package com.example.jetpackcompose.State

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StateFulExample : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateFull(this@StateFulExample)
        }
    }
}


@Composable
fun StateFull(context: StateFulExample?) {

    var value by remember { mutableStateOf(false) }
    var value12 by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value.toString(),   // Shows true/false
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Blue
            )

            Button(
                onClick = {
                    // value = !value   // Toggle boolean

                    if (value) {
                        value = false
                    } else {
                        value = true
                    }
                },
                // shape = RoundedCornerShape(5.dp),
                modifier = Modifier.background(
                    shape = RoundedCornerShape(5.dp),
                    color = Color.Green
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (value) Color.Black else Color.Red
                )
            ) {
                Text(
                    text = "Button",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }

            Button(
                onClick = {
                    //value12++
                    // Toast.makeText(context, "Count $value12", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, StateLessExample::class.java)
                    intent.putExtra("value",++value12)
                    context?.startActivity(intent)

                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Count $value12",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

            }


        }
    }
}

@Composable
@Preview(showBackground = true)
fun Show() {
    //StateFull(context =)
}




