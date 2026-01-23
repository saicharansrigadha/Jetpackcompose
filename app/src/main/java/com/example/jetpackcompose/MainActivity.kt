package com.example.jetpackcompose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcompose.LazyColumn.Model
import com.example.jetpackcompose.ViewModel.DemoViewModel
import java.util.ArrayList

class MainActivity : ComponentActivity() {

    var myviewmodel: DemoViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myviewmodel = ViewModelProvider(this).get(DemoViewModel::class.java)
        //myviewmodel?.navigator = this


//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                )
        //SetName("JetPack Compose", 25, myviewmodel!!)

        val data=intent.getSerializableExtra("data") as ArrayList<Model>
        setContent {
//            DemoTheme {
//                val data = myviewmodel?.userData?.value
//                SetName(
//                    name = data?.firstName ?: "",   // default empty until clicked
//                    age = data?.id ?: 0,            // default 0
//                    myviewmodel = myviewmodel!!
//                )
//            }
            //BasicTextTheme()
            SimpleOutlinedTextField()

            if (data != null) {
                BasicText(name = data)
            }


        }
    }

    /* Old approach and not recommend using navigator

    override fun getData(data: UserData) {

        //runOnUiThread {
            setContent {
                DemoTheme {
                    SetName(name = data.firstName!!, age = data.id!!, myviewmodel = myviewmodel!!)
                }
            }
       // }
    }
    */

    fun message() {
        Toast.makeText(this@MainActivity, "Data Successfully Fetched", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SetName(name: String, age: Int, myviewmodel: DemoViewModel) {
    val context = LocalContext.current   // <-- Needed for Toast
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFD8D4E0)),
        verticalArrangement = Arrangement.Top,  //Top,Bottom
        horizontalAlignment = Alignment.CenterHorizontally,  //Start,End,CenterHorizontally
    )
    {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically  //Top,CenterVertically,Bottom
        ) {
            Text(
                text = "Hello World!",
                color = Color.Red,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked Hello World", Toast.LENGTH_SHORT)
                            .show()
                    },
                style = TextStyle(
                    background = Color.Green
                )
            )

            Text(
                text = "$name",
                color = Color.Red,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked $name", Toast.LENGTH_SHORT)
                            .show()
                    },
                style = TextStyle(
                    background = Color.Green
                )
            )
            Text(
                text = "Android",
                color = Color(android.graphics.Color.parseColor("#FFFFFF")),  //Type 2
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                style = TextStyle(
                    background = Color.DarkGray
                )
            )
            Text(
                text = "$age",
                color = Color.Black,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(10.dp),
                style = TextStyle(
                    background = Color.Magenta
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = "Charan",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(10.dp)
            )
        }
        // 🔵 ADDING BUTTON HERE
        Button(
            onClick = {
                //viewModel.getSummaryData()
                myviewmodel?.getSummaryData()
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Click Me")
        }


    }


}


@Composable
fun BasicText(name: List<Model>){

}


@Preview(
    name = "Profile Screen Preview",
    showBackground = true,
    device = "id:pixel_6_pro"
)
@Composable
fun SetNamePreview() {
    SetName(
        name = "Preview Name",
        age = 25,
        myviewmodel = DemoViewModel()
    )
}

//Modifier is use to change the padding and background color of the app
