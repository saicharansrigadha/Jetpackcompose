package com.example.jetpackcompose.StateHoisting

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StateHoisting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parent()
        }
    }
}

@Composable
fun Parent() {
    var name by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val list = listOf(
        "Sai",
        "Charan",
        "Srigadha"
    )

    val withlist = listOf(
        User(1, "Saicharan", "Srigadha@gmail.com")
    )

    StateHoistingExample(
        name = name,
        result = result,
        onValueChange = { name = it },
        onResult = { result = it },
        list = list,
        withlist = withlist,
        onClearName={
            name=""
        }
    )
}

@Composable
fun StateHoistingExample(
    name: String,
    onValueChange: (String) -> Unit,
    result: String,
    onResult: (String) -> Unit,
    list: List<String>,
    withlist: List<User>,
    onClearName:() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var value1 by remember { mutableStateOf("") }
        var user by remember { mutableStateOf(User(0)) }
        var userList = remember { mutableStateListOf<User>() }

        BasicTextField(
            value = name,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
                .height(35.dp)
                .background(Color.Cyan)
                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp),

            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (name.isEmpty()) {
                        Text(
                            text = "Enter name",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            }
        )

        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            placeholder = { Text("Enter name") },
            label = { Text("Name") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                onResult(name)
                user = user.copy(id = 0, name = name, email = value1)
                userList?.add(user)
                onClearName()
                value1 = ""
            },
            modifier = Modifier
                .wrapContentWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Login")
        }

        Text(
            text = user.name.toString(),
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 20.sp)
        )
        Text(
            text = user.email.toString(),
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 20.sp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            itemsIndexed(list) { index, name ->
                TextCell(text = "$index - $name")
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            items(withlist) {
                TextCell(text = it.name.toString())
            }


        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            items(userList) {
                TextCell(text = it.name.toString() + "" + it.email.toString())
            }


        }


    }
}

@Composable
fun TextCell(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun Show() {
    StateHoistingExample(
        "",
        {

        },
        result = "",
        onResult = {

        },
        emptyList<String>(),
        emptyList<User>(),
        onClearName={

        }

    )
}