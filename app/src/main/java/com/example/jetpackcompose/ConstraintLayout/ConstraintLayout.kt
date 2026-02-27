package com.example.jetpackcompose.ConstraintLayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


class ConstraintLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ConstraintExample(onClick = {
                Toast.makeText(this@ConstraintLayout, "Constraint", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Composable
fun ConstraintExample(
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()

    ) {
        val (tvTitle, btnSubmit, next) = createRefs()               //create references to use the constraint layout in compose

        Text(
            text = "Login",
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvTitle) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(color = Color.Blue)
                .border(1.dp, color = Color.Red, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(50.dp))
                .padding(15.dp)

        )

        Button(
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .constrainAs(btnSubmit) {
                    top.linkTo(tvTitle.bottom, 20.dp)
                    start.linkTo(tvTitle.start)
                    end.linkTo(tvTitle.end)
                },
            border = BorderStroke(2.dp, color = Color.Yellow),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.DarkGray,
                containerColor = Color.Green
            )
        ) {
            Text("Submit")
        }
        Text(
            text = "Next",
            color = Color.Blue,
            modifier = Modifier
                .constrainAs(next) {
                    top.linkTo(btnSubmit.bottom, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Green)
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)).padding(10.dp)

        )

    }
}

@Preview
@Composable
fun ShowConstraint() {
    ConstraintExample(onClick = {

    })
}