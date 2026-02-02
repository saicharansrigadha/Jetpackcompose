package com.example.jetpackcompose.LazyColumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.LazyColumn.ui.theme.JetpackComposeTheme
import java.io.Serializable

class NextActivity : ComponentActivity() {
    var data: String?=null
    var filterdata:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val selectedList = intent.getSerializableExtra("data1") as ArrayList<Model>

        
        val filterlist=intent.getSerializableExtra("filterdata") as ArrayList<Model>

        var isSelected=intent.getBooleanExtra("isselected",false)
        
        selectedList?.forEach {
             data=it.name
        }

        filterlist?.forEach {
            filterdata=it.name
        }
        setContent {
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        data=filterdata?:"",
                        isSelected=isSelected,
                        name = data?:"",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, data: Serializable, isSelected: Boolean) {
    Text(
        text = "Hello $name! $isSelected",
        modifier = modifier
    )

    Text(
        text = "World $data!",
        modifier = modifier.fillMaxWidth().padding(top = 50.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        Greeting("Android", modifier = {} as Modifier, "",false)
    }
}