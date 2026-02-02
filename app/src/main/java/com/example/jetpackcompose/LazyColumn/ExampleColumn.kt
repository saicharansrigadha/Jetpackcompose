package com.example.jetpackcompose.LazyColumn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.jvm.java

class ExampleColumn : ComponentActivity() {

    // var list:ArrayList<Model>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val navController = rememberNavController()  //For  Navigation


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
                        Model("Saicharan", false),
                        Model("Srigadha", false),
                        Model("Android", false),
                        Model("Compose", false)
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
    list: MutableList<Model>,
    checked: Boolean,
    onChanged: (Boolean) -> Unit,
) {
    var context= LocalContext.current

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


        var selectedIndex by remember { mutableStateOf(-1) }


        LazyColumn {
            itemsIndexed(list) { index, item ->
                ColumnCell(
                    name = item.name,
                    isSelected = selectedIndex == index, // ✅ KEY CHANGE
//                    onClick = {
//                        //item.isSelected =!item.isSelected
//                        list[index] = item.copy(
//                            isSelected = !item.isSelected          /*  Multiple Selection */
//                        )
//                    }


                    onClick = {
                        for (i in list.indices) {
                            list[i].isSelected = false
                        }
                        if (selectedIndex == index) {
                            selectedIndex = -1
                        } else {
                            list[index].isSelected = true
                            selectedIndex = index
                        }

//                        if (selectedIndex != -1) {
//                            val selectedItem = list[selectedIndex]
//                            val intent = Intent(context, NextActivity::class.java)
//                            intent.putExtra("selected_name", selectedItem.name)
//                            context.startActivity(intent)
//                        }


                    },
                )
            }
        }
        // 🔹 NEXT BUTTON
        Text(
            text = "Next",
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black)
                .clickable {
                    // ✅ single object
                    val selectedItem = list[selectedIndex]

                    // ✅ list of selected objects
                    val selectedList = ArrayList<Model>()
                    for (i in list.indices) {
                        if (list[i].isSelected) {
                            selectedList.add(list[i])
                        }
                    }

                    val filterdata=list?.filter { it.isSelected==true }

                    val intent = Intent(context, NextActivity::class.java)
                    intent.putExtra("data", selectedItem)      // single object
                    intent.putExtra("data1", selectedList)     // object list
                    intent.putExtra("filterdata",filterdata as ArrayList<Model>)
                    intent.putExtra("isselected",checked)
                    context.startActivity(intent)
                }
                .padding(vertical = 12.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )


    }
}
@Composable
fun ColumnCell(name: String?, isSelected: Boolean, onClick: () -> Unit) {
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
                if (isSelected) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
            .padding(10.dp)
            .clickable {
                onClick()
            }
    )

    //border is use to add color for the whole text or box with border
    // stroke and shape of the design
}