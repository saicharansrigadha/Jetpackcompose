package com.example.jetpackcompose.LazyColumn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.MainActivity

class ExampleColumn : ComponentActivity() {

    // var list:ArrayList<Model>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //    list?.add(Model("Saicharan"))

        val list = arrayListOf(
            Model("Saicharan"),
            Model("Srigadha"),
            Model("Android"),
            Model("Compose")
        )
        setContent {
            var isChecked by remember { mutableStateOf(false) }
            ExColumn(
                this@ExampleColumn,
                list,
                isChecked,
                onChanged = {
                    isChecked = it
                    Toast.makeText(this@ExampleColumn, "Checked", Toast.LENGTH_SHORT).show()
                },
               onItemClick = {
                   val i= Intent(this@ExampleColumn, MainActivity::class.java)
                   i.putExtra("data",list)
                   startActivity(i)
               }

            )
        }
    }
}

@Composable
fun ExColumn(
    context: ExampleColumn,
    list: List<Model>,
    checked: Boolean,
    onChanged: (Boolean) -> Unit,
    onItemClick:(Model) -> Unit

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Column",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )

        Checkbox(
            checked = checked,
            onCheckedChange = {
                onChanged(it)
            },
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(list) { item ->
                ColumnCell(name = item.name,
                    onClick = {
                        onItemClick(item)
                        Toast.makeText(context, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
                    }

                )

            }
        }

    }
}

@Composable
fun ColumnCell(name: String?, onClick: () -> Unit) {
    Text(
        text = name ?: "",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(5.dp).
        clickable{
            onClick()
        }
    )
}