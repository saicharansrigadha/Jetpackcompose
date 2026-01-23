package com.example.jetpackcompose.LazyColumn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.MainActivity
import kotlin.jvm.java

class ExampleColumn : ComponentActivity() {

    // var list:ArrayList<Model>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //    list?.add(Model("Saicharan"))
//
//        val list = arrayListOf(
//            Model("Saicharan",false),
//            Model("Srigadha",false),
//            Model("Android",false),
//            Model("Compose",false)
//        )



        setContent {
            var isChecked by remember { mutableStateOf(false) }
            ExColumn(
                this@ExampleColumn,
                list = remember {
                mutableStateListOf(
                    Model("Saicharan"),
                    Model("Srigadha"),
                    Model("Android"),
                    Model("Compose")
                )
            },
                isChecked,
                onChanged = {
                    isChecked = it
                    Toast.makeText(this@ExampleColumn, "Checked", Toast.LENGTH_SHORT).show()
                },
//                onItemClick = {
//                    val i = Intent(this@ExampleColumn, MainActivity::class.java)
//                    i.putExtra("data", list)
//                    startActivity(i)
//                }

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
    //onItemClick: (Model) -> Unit

) {
    val selectedItems = remember {
        mutableStateOf(setOf<Int>())
    }

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
        LazyColumn {
            itemsIndexed(list) { index, item ->
                ColumnCell(
                    name = item.name,
                    isSelected = item.isSelected,
                    onClick = {
                        // update business state
                        item.isSelected = !item.isSelected

                        // 🔥 notify Compose
                        list[index] = item
                    }
                )
            }
        }



    }
}

@Composable
fun ColumnCell(name: String?,isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = name ?: "",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .background(
                if (isSelected){
                    Color.Green
                } else{
                    Color.Red
                }
            ).
            padding(10.dp)
            .clickable {
                onClick()
            }
    )

    //border is use to add color for the whole text or box with border
    // stroke and shape of the design
}